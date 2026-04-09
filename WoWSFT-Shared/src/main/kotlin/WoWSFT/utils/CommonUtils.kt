package WoWSFT.utils

import WoWSFT.model.Constant.*
import WoWSFT.model.gameparams.CommonModifierShip
import WoWSFT.model.gameparams.ship.component.artillery.Shell
import WoWSFT.model.gameparams.ship.component.planes.Plane
import WoWSFT.model.gameparams.ship.component.torpedo.TorpedoAmmo
import WoWSFT.utils.CoreJsonUtils.decodeRaw
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import kotlinx.serialization.decodeFromString

object CommonUtils {
    private val mapper = ObjectMapper()

    fun getDist(number: Number): Double {
        return CoreMathUtils.getDist(number)
    }

    fun getDistCoefWG(number: Number): Double {
        return CoreMathUtils.getDistCoefWG(number)
    }

    fun getBonusCoef(number: Number): Double {
        return CoreMathUtils.getBonusCoef(number)
    }

    fun getBonusCoeffInverse(number: Number): Double {
        return CoreMathUtils.getBonusCoeffInverse(number)
    }

    fun getBonusCoeffPercent(number: Number): Double {
        return CoreMathUtils.getBonusCoeffPercent(number)
    }

    fun getBonus(number: Number): Double {
        return CoreMathUtils.getBonus(number)
    }

    fun replaceZero(number: String): String {
        return CoreMathUtils.replaceZero(number)
    }

    fun getNumSym(number: Number): String {
        return CoreMathUtils.getNumSym(number)
    }

    fun getDecimalRounded(num: Double, digits: Int): Double {
        return CoreMathUtils.getDecimalRounded(num, digits)
    }

    @Throws(IOException::class)
    fun zFetch(zf: ZipFile, index: String, obj: Class<*>?): Any? {
        val zipEntry: ZipEntry? = zf.getEntry("${index}${FILE_JSON}")
        if (zipEntry == null) {
            return null
        }

        return when (obj) {
            Plane::class.java -> CoreJsonUtils.gameParamsJson.decodeFromString<Plane>(zf.getInputStream(zipEntry).bufferedReader().use { it.readText() })
            Shell::class.java -> CoreJsonUtils.gameParamsJson.decodeFromString<Shell>(zf.getInputStream(zipEntry).bufferedReader().use { it.readText() })
            TorpedoAmmo::class.java -> CoreJsonUtils.gameParamsJson.decodeFromString<TorpedoAmmo>(zf.getInputStream(zipEntry).bufferedReader().use { it.readText() })
            else -> mapper.readValue(zf.getInputStream(zipEntry), obj)
        }
    }

    fun getBonus(copy: LinkedHashMap<String, Any>, isConsumable: Boolean = false): LinkedHashMap<String, String> {
        val bonus = LinkedHashMap<String, String>()

        copy.forEach { (param, cVal) ->
            if (cVal is LinkedHashMap<*, *>) {
                decodeRaw<CommonModifierShip>(cVal).let { cValConvert ->
                    if (cValConvert.aircraftCarrier != 1.0 && cValConvert.aircraftCarrier != 0.0) {
                        getBonus(linkedMapOf(Pair(param, cValConvert.aircraftCarrier))).forEach { (convertKey, convertVal) ->
                            bonus["${convertKey}_${AIRCARRIER.uppercase()}"] = convertVal
                        }
                    }

                    if (cValConvert.battleship != 1.0 && cValConvert.battleship != 0.0) {
                        getBonus(linkedMapOf(Pair(param, cValConvert.battleship))).forEach { (convertKey, convertVal) ->
                            bonus["${convertKey}_${BATTLESHIP.uppercase()}"] = convertVal
                        }
                    }

                    if (cValConvert.cruiser != 1.0 && cValConvert.cruiser != 0.0) {
                        getBonus(linkedMapOf(Pair(param, cValConvert.cruiser))).forEach { (convertKey, convertVal) ->
                            bonus["${convertKey}_${CRUISER.uppercase()}"] = convertVal
                        }
                    }

                    if (cValConvert.destroyer != 1.0 && cValConvert.destroyer != 0.0) {
                        getBonus(linkedMapOf(Pair(param, cValConvert.destroyer))).forEach { (convertKey, convertVal) ->
                            bonus["${convertKey}_${DESTROYER.uppercase()}"] = convertVal
                        }
                    }
                }
            } else if (param.contains("visionXRay")) {
                bonus["${MODIFIER}${param.uppercase()}"] = "${getDist(cVal as Double)} km"
            } else if (meter.any { param.contains(it, true) } && isConsumable) {
                bonus["${MODIFIER}${param.uppercase()}"] = "${getDistCoefWG(cVal as Double)} km"
            } else if (time.any { param.endsWith(it, true) } || (timeConsumables.any { param.endsWith(it, true) } && isConsumable)) {
                bonus["${MODIFIER}${param.uppercase()}"] = "${replaceZero(cVal.toString())} s"
            } else if (param.startsWith("lastChance")) {
                bonus["${MODIFIER}${param.uppercase()}"] = "${replaceZero(cVal.toString())} %"
            } else if (rate.any { param.contains(it, true) } || (repair.any { param.contains(it, true) } && isConsumable)) {
                bonus["${MODIFIER}${param.uppercase()}"] = "${getNumSym(getBonus(cVal as Double))} %"
            } else if (coeffPercent.any { param.contains(it, true) }) {
                bonus["${MODIFIER}${param.uppercase()}"] = "${getNumSym(getBonusCoeffPercent(cVal as Double))} %"
            } else if (coeffInverse.any { param.contains(it, true) }) {
                bonus["${MODIFIER}${param.uppercase()}"] = "${getNumSym(getBonusCoeffInverse(cVal as Double))} %"
            } else if (param.equals("affectedClasses", ignoreCase = true)) {
                (cVal as? List<*>)
                    ?.map { it.toString() }
                    .takeIf { it.isNullOrEmpty().not() }
                    ?.let { list ->
                        bonus["${MODIFIER}${param.uppercase()}"] = list.joinToString { affected -> "${IDS_}${affected.uppercase()} " }.trim()
                    }
            } else if (noUnit.any { param.contains(it, true) }) {
                bonus["${MODIFIER}${param.uppercase()}"] = if (cVal as Double > 0) replaceZero(cVal.toString()) else "-"
            } else if (cVal is Int || extra.any { param.contains(it, true) }) {
                bonus["${MODIFIER}${param.uppercase()}"] = getNumSym(cVal as Number)
            } else if (cVal is Double) {
                bonus["${MODIFIER}${param.uppercase()}"] = "${getNumSym(getBonusCoef(cVal))} %"
            }
        }

        return bonus
    }
}
