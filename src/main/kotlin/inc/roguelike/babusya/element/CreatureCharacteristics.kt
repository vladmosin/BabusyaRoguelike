package inc.roguelike.babusya.element

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName

/**
 * Stores different characteristics of creature
 * creature hit points and max hit points specify creatures current health and health bound
 *  when creature's hit points become less than zero, creature will die
 * on creatures attack based many damage effects, creature can apply
 * */
data class CreatureCharacteristics(var hitPoints: Int, var maxHitPoints: Int, var attack: Int) {
    companion object {
        private const val name = "CreatureCharacteristics"

        /**
         * creates simple characteristic prototype
         */
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
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.name || args.size != 3) {
                null
            } else {
                try {
                    CreatureCharacteristics(
                        args[0].toInt(),
                        args[1].toInt(),
                        args[2].toInt()
                    )
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }
    }

    fun serialize(): String {
        return collectToString(name, listOf(hitPoints.toString(), maxHitPoints.toString(), attack.toString()))
    }

    fun clone(): CreatureCharacteristics {
        return CreatureCharacteristics(hitPoints, maxHitPoints, attack)
    }
}
