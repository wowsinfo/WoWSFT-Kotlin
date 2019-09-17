package wowsft.model.gameparams.modernization

import wowsft.config.WoWSFT
import wowsft.model.gameparams.CommonModifier
import wowsft.model.gameparams.TypeInfo
import com.fasterxml.jackson.annotation.JsonInclude

import java.util.ArrayList
import java.util.LinkedHashMap

@WoWSFT
class Modernization : CommonModifier() {
    var costCR = 0
    var costGold = 0
    var excludes = ArrayList<String>()
    var group = ArrayList<String>()
    var id = 0L
    var index = ""
    var name = ""
    var nation = ArrayList<String>()
    var shiplevel = ArrayList<Int>()
    var ships = ArrayList<String>()
    var shiptype = ArrayList<String>()
    @JsonInclude
    var slot = 0
    var type = 0
    var typeinfo = TypeInfo()

    var fullName = ""
    var image = if (name.isNotEmpty()) "https://cdn.wowsft.com/images/modernization_icons/icon_modernization_$name.png" else ""

    var bonus = LinkedHashMap<String, String>()
    var description = ""
}
