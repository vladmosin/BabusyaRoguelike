package inc.roguelike.babusya.element

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName

/**
 * Stores different characteristics of create
 * */
data class CreatureCharacteristics(var hitPoints: Int, var maxHitPoints: Int, var attack: Int) {
    companion object {
        fun createBasic(): CreatureCharacteristics {
            val maxHitPoints = 100
            val attack = 20

            return CreatureCharacteristics(
                hitPoints = maxHitPoints,
                maxHitPoints = maxHitPoints,
                attack = attack
            )
        }

        fun deserialize(line: String): CreatureCharacteristics? {
            return CreautureCharacteristicsMemento.deserialize(line)
        }
    }

    fun serialize(): String {
        return CreautureCharacteristicsMemento.serialize(this)
    }

    fun clone(): CreatureCharacteristics {
        return CreatureCharacteristics(hitPoints, maxHitPoints, attack)
    }
}
