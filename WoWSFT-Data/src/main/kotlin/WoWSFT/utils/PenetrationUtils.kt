package WoWSFT.utils

import WoWSFT.model.gameparams.ship.component.artillery.Shell

class PenetrationUtils
{
    fun setPenetration(shell: Shell, maxVertAngle: Double, minDistV: Double, maxDist: Double, apShell: Boolean)
    {
        val result = ProjectileBallisticsCalculator.calculate(
            ProjectileBallisticsInput(
                bulletMass = shell.bulletMass,
                bulletDiameter = shell.bulletDiametr,
                bulletAirDrag = shell.bulletAirDrag,
                bulletSpeed = shell.bulletSpeed,
                bulletKrupp = shell.bulletKrupp,
                bulletAlwaysRicochetAt = shell.bulletAlwaysRicochetAt,
                bulletCapNormalizeMaxAngle = shell.bulletCapNormalizeMaxAngle,
                maxVertAngle = maxVertAngle,
                minDistV = minDistV,
                maxDist = maxDist,
                apShell = apShell,
            )
        )

        shell.setShell(
            flightTime = result.flightTime,
            penetration = result.penetration,
            impact = result.impactAngle,
            distanceList = result.distanceList,
            minDistV = result.minDistV,
        )
    }

    fun getMidAtY(x1: Double, y1: Double, x2: Double, y2: Double, yAxis: Double): Double
    {
        return ProjectileBallisticsCalculator.getMidAtY(x1, y1, x2, y2, yAxis)
    }
}
