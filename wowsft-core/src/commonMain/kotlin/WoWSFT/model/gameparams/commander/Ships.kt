package WoWSFT.model.gameparams.commander

import kotlinx.serialization.Serializable

@Serializable
class Ships {
    var nation = mutableListOf<String>()
    var peculiarity = mutableListOf<String>()
    var ships = mutableListOf<String>()
    var groups = mutableListOf<String>()
}
