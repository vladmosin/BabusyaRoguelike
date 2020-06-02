package inc.roguelike.babusya.effects.memento

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.effects.MonsterPunchEffect
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import java.lang.NumberFormatException

/**
 * Memento for for monster punch effect
 * */
class MonsterPunchEffectMemento {
    companion object {
        private const val name = "PunchEffect"

        /**
         * Deserializes effect
         * */
        fun deserialize(line: String): MonsterPunchEffect? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.name || args.size != 1) {
                null
            } else {
                try {
                    MonsterPunchEffect(args[0].toInt())
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }

        /**
         * Serializes effect
         * */
        fun serialize(monsterPunchEffect: MonsterPunchEffect) =
            collectToString(name, listOf(monsterPunchEffect.damage.toString()))
    }
}