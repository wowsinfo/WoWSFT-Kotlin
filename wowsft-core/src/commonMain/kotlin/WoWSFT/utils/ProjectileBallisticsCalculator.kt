package WoWSFT.utils

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sign
import kotlin.math.sin

data class ProjectileBallisticsInput(
    val bulletMass: Double,
    val bulletDiameter: Double,
    val bulletAirDrag: Double,
    val bulletSpeed: Double,
    val bulletKrupp: Double,
    val bulletAlwaysRicochetAt: Double,
    val bulletCapNormalizeMaxAngle: Double,
    val maxVertAngle: Double,
    val minDistV: Double,
    val maxDist: Double,
    val apShell: Boolean,
)

data class ProjectileBallisticsResult(
    val flightTime: LinkedHashMap<String, Double>,
    val penetration: LinkedHashMap<String, Double>,
    val impactAngle: LinkedHashMap<String, Double>,
    val distanceList: MutableList<String>,
    val minDistV: Double,
)

object ProjectileBallisticsCalculator {
    private const val gravity = 9.80665
    private const val seaLevelTemperature = 288.15
    private const val temperatureLapseRate = 0.0065
    private const val seaLevelPressure = 101325.0
    private const val gasConstant = 8.31447
    private const val airMolarMass = 0.0289644
    private const val intervalDeg = 0.10
    private const val deltaTime = 0.045
    private const val penetrationConstant = 0.5561613
    private const val quadraticDragCoefficient = 1.0

    fun calculate(input: ProjectileBallisticsInput): ProjectileBallisticsResult {
        var effectiveKrupp = penetrationConstant * input.bulletKrupp / 2400.0
        val linearDragCoefficient = 100.0 + 1000.0 / 3.0 * input.bulletDiameter
        val ricochet = (input.bulletAlwaysRicochetAt + input.bulletCapNormalizeMaxAngle) * PI / 180.0
        val dragFactor = 0.5 * input.bulletAirDrag * (input.bulletDiameter / 2.0).pow(2.0) * PI / input.bulletMass

        val flightTime = linkedMapOf<String, Double>()
        val penetration = linkedMapOf<String, Double>()
        val impactAngle = linkedMapOf<String, Double>()
        val distanceList = mutableListOf<String>()

        linspace(input.maxVertAngle).forEach { alpha ->
            val angle = CoreMathUtils.getDecimalRounded(alpha * PI / 180.0, 9)
            var velocityX = cos(angle) * input.bulletSpeed
            var velocityY = sin(angle) * input.bulletSpeed
            var y = 0.0
            var x = 0.0
            var time = 0.0

            while (y >= 0) {
                x += deltaTime * velocityX
                y += deltaTime * velocityY

                val temperature = seaLevelTemperature - temperatureLapseRate * y
                val pressure = seaLevelPressure * (1 - temperatureLapseRate * y / seaLevelTemperature).pow(gravity * airMolarMass / (gasConstant * temperatureLapseRate))
                val density = pressure * airMolarMass / (gasConstant * temperature)

                velocityX -= deltaTime * dragFactor * density * (quadraticDragCoefficient * velocityX.pow(2.0) + linearDragCoefficient * velocityX)
                velocityY = velocityY - deltaTime * gravity - deltaTime * dragFactor * density * (quadraticDragCoefficient * velocityY.pow(2.0) + linearDragCoefficient * abs(velocityY)) * sign(velocityY)
                time += deltaTime
            }

            val totalVelocity = (velocityY.pow(2.0) + velocityX.pow(2.0)).pow(0.5)
            val impactPenetration = effectiveKrupp * totalVelocity.pow(1.1) * input.bulletMass.pow(0.55) / (input.bulletDiameter * 1000.0).pow(0.65)
            val impactRadians = atan(abs(velocityY) / abs(velocityX))

            if (impactRadians > ricochet || x > input.maxDist * 1.25 || x > 25000) {
                return@forEach
            }

            val roundedDistance = CoreMathUtils.getDecimalRounded(x, 4)
            val distanceKey = roundedDistance.toString()
            flightTime[distanceKey] = CoreMathUtils.getDecimalRounded(time / 3.0, 5)

            if (input.apShell) {
                penetration[distanceKey] = CoreMathUtils.getDecimalRounded(cos(impactRadians) * impactPenetration, 5)
                impactAngle[distanceKey] = CoreMathUtils.getDecimalRounded(impactRadians * 180.0 / PI, 5)
                distanceList.add(distanceKey)
            }
        }

        return ProjectileBallisticsResult(
            flightTime = flightTime,
            penetration = penetration,
            impactAngle = impactAngle,
            distanceList = distanceList,
            minDistV = input.minDistV,
        )
    }

    fun getMidAtY(x1: Double, y1: Double, x2: Double, y2: Double, yAxis: Double): Double {
        val slope = (y2 - y1) / (x2 - x1)
        val intercept = y1 - slope * x1
        return (yAxis - intercept) / slope
    }

    private fun linspace(end: Double): List<Double> {
        var countDeg = 0.0
        val alpha = mutableListOf<Double>()

        while (countDeg <= end) {
            alpha.add(countDeg)
            countDeg = CoreMathUtils.getDecimalRounded(countDeg + intervalDeg, 2)
        }

        return alpha
    }
}
