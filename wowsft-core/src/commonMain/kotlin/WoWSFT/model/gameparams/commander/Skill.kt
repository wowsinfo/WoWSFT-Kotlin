package WoWSFT.model.gameparams.commander

import kotlinx.serialization.Serializable
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonNames


import WoWSFT.model.Constant.CDN_IMAGE
import WoWSFT.model.gameparams.CommonModifier

@OptIn(ExperimentalSerializationApi::class)
@Serializable
class Skill
{
    var tier = SkillTier()
    var skillType = 0
    var canBeLearned = true
    @JsonNames("isEpic")
    var epic = false
    var modifiers = CommonModifier()
    var bonus = LinkedHashMap<String, String>()
    var description = ""
    @JsonNames("LogicTrigger")
    var logicTrigger = LogicTrigger()
    var name = ""
    var nameSplit = ""
    val image get() = "$CDN_IMAGE/skills/$nameSplit.png"

    var consumableDescID = ""
    var consumableIconID = ""
    var consumableTitleID = ""
}