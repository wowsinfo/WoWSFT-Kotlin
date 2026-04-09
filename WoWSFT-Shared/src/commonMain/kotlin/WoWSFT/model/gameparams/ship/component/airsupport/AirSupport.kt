package WoWSFT.model.gameparams.ship.component.airsupport

import WoWSFT.model.gameparams.ship.component.planes.Plane
import kotlinx.serialization.Serializable

@Serializable
class AirSupport {
    var chargesNum = 0
    var climbAngle = 0
    var flyAwayTime = 0.0
    var maxDist = 0
    var maxPlaneFlightDist = 0
    var minDist = 0
    var planeName = ""
    var reloadTime = 0.0
    var timeBetweenShots = 0.0
    var timeFromHeaven = 0.0
    var plane: Plane? = null
}
