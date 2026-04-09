package WoWSFT.model.gameparams.ship.component.airarmament

import kotlinx.serialization.Serializable

@Serializable
class AirArmament {
    var auraCoeff = 0.0
    var deckPlaceCount = 0
    var independentLaunchpad = false
    var launchPrepareTime = 0.0
    var launchpadType = ""
    var planesReserveCapacity = 0
}
