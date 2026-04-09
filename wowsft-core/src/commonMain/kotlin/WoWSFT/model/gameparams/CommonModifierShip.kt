package WoWSFT.model.gameparams

import kotlinx.serialization.Serializable
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.JsonNames



@OptIn(ExperimentalSerializationApi::class)
@Serializable
class CommonModifierShip {
    constructor()
    constructor(value: Double) {
        aircraftCarrier = value
        auxiliary = value
        battleship = value
        cruiser = value
        destroyer = value
        submarine = value
    }

    @JsonNames("AirCarrier")
    var aircraftCarrier = 1.0

    @JsonNames("Auxiliary")
    var auxiliary = 1.0

    @JsonNames("Battleship")
    var battleship = 1.0

    @JsonNames("Cruiser")
    var cruiser = 1.0

    @JsonNames("Destroyer")
    var destroyer = 1.0

    @JsonNames("Submarine")
    var submarine = 1.0
}