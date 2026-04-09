package WoWSFT.model.gameparams.modernization

import kotlinx.serialization.Serializable


import WoWSFT.model.Constant.CDN_IMAGE
import WoWSFT.model.gameparams.CommonModifier
import WoWSFT.model.gameparams.TypeInfo

@Serializable
class Modernization
{
    var excludes = mutableListOf<String>()
    var group = mutableListOf<String>()
    var id: Long = 0
    var index = ""
    var name = ""
    var nation = mutableListOf<String>()
    var shiplevel = mutableListOf<Int>()
    var ships = mutableListOf<String>()
    var shiptype = mutableListOf<String>()
        var slot = 0
    var type = 0
    var typeinfo = TypeInfo()
    var fullName = ""
    val image get() = "$CDN_IMAGE/modernization_icons/icon_modernization_$name.png"
    var bonus = LinkedHashMap<String, String>()
    var description = ""
    var modifiers = CommonModifier()
    var costCR = 0
    var costGold = 0

    var tags = mutableListOf<String>()
}