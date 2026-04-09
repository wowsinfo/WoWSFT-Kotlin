package WoWSFT.model.gameparams.ship.component.planes

import WoWSFT.model.gameparams.TypeInfo
import WoWSFT.model.gameparams.consumable.Consumable
import WoWSFT.model.gameparams.ship.abilities.AbilitySlot
import WoWSFT.model.gameparams.ship.component.artillery.Shell
import WoWSFT.model.gameparams.ship.component.torpedo.TorpedoAmmo
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
class Plane {
    @JsonNames("PlaneAbilities")
    var planeAbilities = LinkedHashMap<String, AbilitySlot>()
    var attackCount = 0
    var attackerSize = 0
    var bombName = ""
    var forsageRegeneration = 0.0
    var fuelTime = 0.0
    var hangarSettings: HangarSetting? = null
    var id = 0L
    var index = ""
    var level = 0
    var maxForsageAmount = 0.0
    var maxHealth = 0
    var maxVisibilityFactor = 0.0
    var maxVisibilityFactorByPlane = 0.0
    var minVisibilityFactor = 0.0
    var minVisibilityFactorByPlane = 0.0
    var name = ""
    var numPlanesInSquadron = 0
    var speedMax = 0.0
    var speedMin = 0.0
    var speedMove = 0.0
    var speedMoveWithBomb = 0.0
    var typeinfo = TypeInfo()
    var rocket = Shell()
    var bomb = Shell()
    var skip = Shell()
    var torpedo = TorpedoAmmo()
    var consumables = mutableListOf<Consumable>()
    var projectilesPerAttack = 0
    var attackCooldown = 0

    val planeType: String
        get() = when (typeinfo.species) {
            "Fighter" -> "Fighter"
            "Dive" -> "DiveBomber"
            "Bomber" -> "TorpedoBomber"
            "Skip" -> "Skip"
            else -> ""
        }

    val planeTypeShort: String
        get() = when (typeinfo.species) {
            "Fighter" -> "f"
            "Dive" -> "d"
            "Bomber" -> "t"
            "Skip" -> "s"
            else -> ""
        }

    val ammoType: String
        get() = when (typeinfo.species) {
            "Fighter" -> "rocket"
            "Dive" -> "bomb"
            "Bomber" -> "torpedo"
            "Skip" -> "skipBomb"
            else -> ""
        }
}
