package WoWSFT.model.gameparams.ship.component.atba

import WoWSFT.model.gameparams.TypeInfo
import kotlinx.serialization.Serializable
import kotlin.math.floor

@Serializable
class Secondary {
    var ammoList = listOf<String>()
    var antiAirAuraDistance = 0.0
    var antiAirAuraStrength = 0.0
    var barrelDiameter = 0.0
    var deadZone = listOf<List<Double>>()
    var pitchDeadZones = listOf<List<Double>>()
    var delim = 0.0
    var horizSector = listOf<Double>()
    var id = 0L
    var idealDistance = 0.0
    var idealRadius = 0.0
    var idealRadiusModifier = 1.0
    var index = ""
    var minRadius = 0.0
    var name = ""
    var numBarrels = 0
    var radiusOnDelim = 0.0
    var radiusOnMax = 0.0
    var radiusOnZero = 0.0
    var rotationSpeed = listOf<Double>()
    var shotDelay = 0.0
    var smallGun = false
    var smokePenalty = 0.0
    var typeinfo = TypeInfo()
    var vertSector = listOf<Double>()
    var GSIdealRadius = 1.0
    var count = 0
    var alphaDamage = 0.0
    var alphaPiercingHE = 0.0
    var ammoType = ""
    var bulletSpeed = 0.0
    var burnProb = 0.0
        set(value) {
            field = value
            burnProbReal = if (burnProbReal == 0.0) value else burnProbReal
        }
    var burnProbReal = 0.0
    val alphaPiercingHEReal get() = floor(alphaPiercingHE).toInt()
}
