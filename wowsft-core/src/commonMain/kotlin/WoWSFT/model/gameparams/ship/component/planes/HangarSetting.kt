package WoWSFT.model.gameparams.ship.component.planes

import kotlinx.serialization.Serializable

@Serializable
class HangarSetting {
    var maxValue = 0
    var restoreAmount = 0
    var startValue = 0
    var timeToRestore = 0.0
}
