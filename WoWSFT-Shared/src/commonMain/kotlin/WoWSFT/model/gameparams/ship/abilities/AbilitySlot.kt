package WoWSFT.model.gameparams.ship.abilities

import kotlinx.serialization.Serializable

@Serializable
class AbilitySlot {
    var abils = mutableListOf<MutableList<String>>()
    var slot = 0
}
