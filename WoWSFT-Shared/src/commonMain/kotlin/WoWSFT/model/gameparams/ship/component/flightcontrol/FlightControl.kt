package WoWSFT.model.gameparams.ship.component.flightcontrol

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
class FlightControl {
    var consumablePlanesReserve = LinkedHashMap<String, Int>()
    var consumableSquadrons = listOf<String>()
    var hasConsumablePlanes = false
    @JsonNames("isGroundAttackEnabled")
    var groundAttackEnabled = false
    var planesReserveAssignment = LinkedHashMap<String, Int>()
    var prepareTimeFactor = 0.0
    var squadrons = listOf<String>()
}
