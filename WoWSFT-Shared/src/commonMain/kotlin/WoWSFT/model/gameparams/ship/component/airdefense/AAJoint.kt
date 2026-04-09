package WoWSFT.model.gameparams.ship.component.airdefense

import kotlinx.serialization.Serializable

@Serializable
data class AAJoint(
    val auraFar: MutableList<Aura> = mutableListOf(),
    val auraMedium: MutableList<Aura> = mutableListOf(),
    val auraNear: MutableList<Aura> = mutableListOf()
)
