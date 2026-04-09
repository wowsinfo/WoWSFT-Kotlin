package WoWSFT.model.gameparams.ship.component.airdefense

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
class Aura {
    var areaDamage = 0.0
    var areaDamagePeriod = 0.0
    var bubbleDamage = 0.0
    var bubbleDuration = 0.0
    var bubbleRadius = 0.0
    var enableBarrage = false
    var enableCrewSelectedTargetCoeff = false
    var explosionCount = 0.0
    var guns = listOf<String>()
    var halfOuterBubbleZone = 0.0
    var hitChance = 0.0
    var innerBubbleCount = 0
    var innerBubbleSpawnTimeRange = listOf<Double>()
    var innerBubbleZone = 0.0
    @JsonNames("isJoint")
    var joint = false
    var maxBubbleActivationDelay = 0.0
    var maxDistance = 0.0
    var maxDistanceStartWorkGap = 0.0
    var minBubbleActivationDelay = 0.0
    var minDistance = 0.0
    var outerBubbleCount = 0.0
    var outerBubbleSpawnTimeRange = listOf<Double>()
    var shotDelay = 0.0
    var shotTravelTime = 0.0
    var timeUniversalsOff = 0.0
    var timeUniversalsOn = 0.0
    var type = ""
    var bubbleDamageModifier = 7.0
}
