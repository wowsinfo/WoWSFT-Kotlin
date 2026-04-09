package WoWSFT.model.gameparams.consumable

import WoWSFT.model.gameparams.TypeInfo
import WoWSFT.utils.CoreJsonUtils
import kotlinx.serialization.Serializable

@Serializable
class Consumable {
    var subConsumables = LinkedHashMap<String, ConsumableSub>()
    var canBuy = false
    var canBuyCustom = LinkedHashMap<String, Boolean>()
    var costCR = 0.0
    var costGold = 0.0
    var freeOfCharge = false
    var id = 0L
    var index = ""
    var name = ""
    var typeinfo = TypeInfo()
    var description = ""

    companion object {
        private val rawConsumableKeys = setOf(
            "canBuy",
            "canBuyCustom",
            "costCR",
            "costGold",
            "freeOfCharge",
            "id",
            "index",
            "name",
            "typeinfo",
            "description"
        )

        fun fromRaw(value: Any?): Consumable {
            val jsonObject = CoreJsonUtils.run { value.toJsonObject() }
            val consumable = CoreJsonUtils.decodeElement<Consumable>(jsonObject)

            jsonObject
                .filterKeys { it !in rawConsumableKeys }
                .forEach { (key, jsonElement) ->
                    consumable.subConsumables[key] = CoreJsonUtils.decodeElement(jsonElement)
                }

            return consumable
        }
    }
}
