package inc.roguelike.babusya.loot.memento

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.loot.Potion

class PotionMemento {
    companion object {
        private const val className = "Potion"

        fun deserialize(line: String): Potion? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != className || args.size != 2) {
                null
            } else {
                val effect = Effect.deserialize(args[1]) ?: return null
                Potion(args[0], effect)
            }
        }

        fun serialize(potion: Potion): String {
            return collectToString(className, listOf(potion.name, potion.effect.serialize()))
        }
    }
}