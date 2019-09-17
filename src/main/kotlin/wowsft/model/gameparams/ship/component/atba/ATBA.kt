package wowsft.model.gameparams.ship.component.atba

import wowsft.config.WoWSFT
import wowsft.model.gameparams.ship.component.airdefense.Aura
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

import java.util.ArrayList
import java.util.HashMap
import java.util.LinkedHashMap

@WoWSFT
@JsonIgnoreProperties(ignoreUnknown = true)
class ATBA {
    var auraFar = ArrayList<Aura>()
    var auraMedium = ArrayList<Aura>()
    var auraNear = ArrayList<Aura>()

    var turrets = ArrayList<Secondary>()
    var secondaries = LinkedHashMap<String, Secondary>()

    var maxDist = 0f
    var minDistH = 0f
    var minDistV = 0f
    var sigmaCount = 0f
    var taperDist = 0f
    @JsonInclude
    var GSIdealRadius = 1f

    @JsonIgnore
    private val mapper = ObjectMapper()

    @JsonAnySetter
    fun setGuns(name: String, value: Any) {
        if (value is HashMap<*, *>) {
            val tempObject = mapper.convertValue<HashMap<String, Any>>(value, object : TypeReference<HashMap<String, Any>>() {})

            if ("far".equals(tempObject["type"] as String, true)) {
                auraFar.add(mapper.convertValue(value, Aura::class.java))
            } else if ("medium".equals(tempObject["type"] as String, true)) {
                auraMedium.add(mapper.convertValue(value, Aura::class.java))
            } else if ("near".equals(tempObject["type"] as String, true)) {
                auraNear.add(mapper.convertValue(value, Aura::class.java))
            } else if (tempObject.containsKey("HitLocationATBA")) {
                val tempS = mapper.convertValue(value, Secondary::class.java)

                if (secondaries[tempS.name] != null) {
                    secondaries[tempS.name]!!.count = secondaries[tempS.name]!!.count + 1
                } else {
                    tempS.count = 1
                    secondaries[tempS.name] = tempS
                }
            }
        }
    }
}
