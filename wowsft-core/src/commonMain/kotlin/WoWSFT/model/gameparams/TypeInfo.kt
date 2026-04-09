package WoWSFT.model.gameparams

import kotlinx.serialization.Serializable

@Serializable
class TypeInfo {
    var nation: String? = null
    var species: String? = null
    var type: String? = null
}
