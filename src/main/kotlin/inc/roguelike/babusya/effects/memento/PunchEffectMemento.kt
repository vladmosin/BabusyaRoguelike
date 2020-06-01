package inc.roguelike.babusya.effects.memento

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.effects.PunchEffect
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import java.lang.NumberFormatException

class PunchEffectMemento {
    companion object {
        private const val name = "PunchEffect"

        fun deserialize(line: String): PunchEffect? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.name || args.size != 1) {
                null
            } else {
                try {
                    PunchEffect(args[0].toInt())
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }

        fun serialize(punchEffect: PunchEffect) =
            collectToString(name, listOf(punchEffect.damage.toString()))
    }
}