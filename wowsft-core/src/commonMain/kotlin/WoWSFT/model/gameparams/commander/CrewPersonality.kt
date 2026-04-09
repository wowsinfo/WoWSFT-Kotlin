package WoWSFT.model.gameparams.commander

import kotlinx.serialization.Serializable
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonNames



@OptIn(ExperimentalSerializationApi::class)
@Serializable
class CrewPersonality
{
    var dismissable = false
    var hasManyNations = false
    var hasOverlay = false
    var hasRank = false
    @JsonNames("isPerson")
    var person = false
    @JsonNames("isRetrainable")
    var retrainable = false
    @JsonNames("isUnique")
    var unique = false
    var canResetSkillsForFree = false
    var peculiarity = ""
    var personName = ""
    var ships = Ships()
    var costCR = 0
    var costELXP = 0
    var costGold = 0
    var costXP = 0

    var subnation = ""
    var tags = mutableListOf<String>()
    var costGOLD = 0
}