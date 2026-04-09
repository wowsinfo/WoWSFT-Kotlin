package WoWSFT.model.gameparams.commander

import kotlinx.serialization.Serializable
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonNames



@OptIn(ExperimentalSerializationApi::class)
@Serializable
class SkillTier {
    @JsonNames("AirCarrier")
    var aircraftCarrier: Int = 1

    @JsonNames("Auxiliary")
    var auxiliary: Int = 1

    @JsonNames("Battleship")
    var battleship: Int = 1

    @JsonNames("Cruiser")
    var cruiser: Int = 1

    @JsonNames("Destroyer")
    var destroyer: Int = 1

    @JsonNames("Submarine")
    var submarine: Int = 1
}