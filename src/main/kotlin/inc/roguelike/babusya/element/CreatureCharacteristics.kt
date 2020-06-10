package inc.roguelike.babusya.element

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName

/**
 * Stores different characteristics of create
 * */
data class CreatureCharacteristics(var hitPoints: Int, var maxHitPoints: Int, var attack: Int) {
    companion object {
        /**
         * Creates basic characteristics
         * */
        fun createBasic(): CreatureCharacteristics {
            val maxHitPoints = 100
            val attack = 20

            return CreatureCharacteristics(
                hitPoints = maxHitPoints,
                maxHitPoints = maxHitPoints,
                attack = attack
            )
        }

        /**
         * Deserializes creature characteristics
         * */
        fun deserialize(line: String): CreatureCharacteristics? {
            return CreautureCharacteristicsMemento.deserialize(line)
        }
    }

    /**
     * Serializes creature characteristics
     * */
    fun serialize(): String {
        return CreautureCharacteristicsMemento.serialize(this)
    }

    /**
     * Clones creature characteristics
     * */
    fun clone(): CreatureCharacteristics {
        return CreatureCharacteristics(hitPoints, maxHitPoints, attack)
    }
}
