package WoWSFT.model.gameparams.commander

import WoWSFT.model.gameparams.TypeInfo
import WoWSFT.utils.CoreJsonUtils
import WoWSFT.utils.camelCaseToSnakeCaseLower
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
class Commander {
    @JsonNames("CrewPersonality")
    var crewPersonality = CrewPersonality()
    var id = 0L
    var identifier = ""
    var index = ""
    var name = ""
    var typeinfo = TypeInfo()
    @JsonNames("CrewSkills")
    var crewSkills = LinkedHashMap<String, Skill>()

    companion object {
        private val rawCommanderKeys = setOf(
            "CrewPersonality",
            "id",
            "identifier",
            "index",
            "name",
            "typeinfo",
            "CrewSkills"
        )

        fun fromRaw(value: Any?): Commander {
            val jsonObject = CoreJsonUtils.run { value.toJsonObject() }
            val commander = CoreJsonUtils.decodeElement<Commander>(
                CoreJsonUtils.run { jsonObject.withoutKeys(setOf("Skills")) }
            )

            jsonObject["Skills"]?.let { skillsElement ->
                val skills = CoreJsonUtils.decodeElement<LinkedHashMap<String, Skill>>(skillsElement)
                skills.forEach { (skillName, skill) ->
                    skill.name = skillName
                    skill.nameSplit = camelCaseToSnakeCaseLower(skillName)
                    commander.crewSkills[skillName] = skill
                }
            }

            jsonObject
                .filterKeys { it !in rawCommanderKeys && it != "Skills" }
                .forEach { (skillName, skillElement) ->
                    val skill = CoreJsonUtils.decodeElement<Skill>(skillElement)
                    skill.name = skillName
                    skill.nameSplit = camelCaseToSnakeCaseLower(skillName)
                    commander.crewSkills[skillName] = skill
                }

            return commander
        }
    }
}
