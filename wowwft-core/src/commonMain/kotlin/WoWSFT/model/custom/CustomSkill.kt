package WoWSFT.model.custom

import WoWSFT.model.Constant.CDN_IMAGE
import WoWSFT.utils.camelCaseToSnakeCaseLower

class CustomSkill {
    var name: String = ""
        set(value) {
            field = value
            nameSplit = camelCaseToSnakeCaseLower(value)
            image = "$CDN_IMAGE/skills/$nameSplit.png"
        }
    var nameSplit: String = ""
    var image: String = ""
    var tier: Int = 0
    var column: Int = 0
}
