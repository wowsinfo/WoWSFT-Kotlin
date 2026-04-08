package WoWSFT.utils

import WoWSFT.model.Constant.distCoefWG
import kotlin.math.pow
import kotlin.math.roundToInt

object CoreMathUtils {
    fun getDist(number: Number): Double {
        return getDecimalRounded(number.toDouble() / 1000.0, 3)
    }

    fun getDistCoefWG(number: Number): Double {
        return getDecimalRounded(number.toDouble() / distCoefWG, 3)
    }

    fun getBonusCoef(number: Number): Double {
        return getDecimalRounded(number.toDouble() * 100.0, 3) - 100.0
    }

    fun getBonusCoeffInverse(number: Number): Double {
        return getDecimalRounded((2.0 - number.toDouble()) * 100.0, 3) - 100.0
    }

    fun getBonusCoeffPercent(number: Number): Double {
        return number.toDouble()
    }

    fun getBonus(number: Number): Double {
        return getDecimalRounded(number.toDouble() * 100.0, 3)
    }

    fun replaceZero(number: String): String {
        return if (number.endsWith(".0")) number.substring(0, number.length - 2) else number
    }

    fun getNumSym(number: Number): String {
        return (if (number.toFloat() >= 0) "+" else "") + replaceZero(number.toFloat().toString())
    }

    fun getDecimalRounded(num: Double, digits: Int): Double {
        val rounder = 10.0.pow(digits)
        return (num * rounder).roundToInt() / rounder
    }
}
