package inc.roguelike.babusya.effects.memento

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.effects.HealEffect
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import java.lang.NumberFormatException

/**
 * Memento for for heal effect
 * */
class HealEffectMemento {
    companion object {
        private const val name = "HealEffect"

        /**
         * Deserializes heal effect
         * */
        fun deserialize(line: String): HealEffect? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.name || args.size != 1) {
                null
            } else {
                try {
                    HealEffect(args[0].toInt())
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }

        /**
         * Serializes heal effect
         * */
        fun serialize(healEffect: HealEffect): String {
            return collectToString(name, listOf(healEffect.healAmount.toString()))
        }
    }
}