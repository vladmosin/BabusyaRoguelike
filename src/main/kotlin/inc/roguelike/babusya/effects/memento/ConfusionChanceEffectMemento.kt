package inc.roguelike.babusya.effects.memento

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.effects.ConfusionChanceEffect
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import java.lang.NumberFormatException

class ConfusionChanceEffectMemento {
    companion object {
        private const val name = "ConfusionChanceEffect"

        fun deserialize(line: String): ConfusionChanceEffect? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.name || args.size != 2) {
                null
            } else {
                try {
                    ConfusionChanceEffect(args[0].toDouble(), args[1].toInt())
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }

        fun serialize(confusionChanceEffect: ConfusionChanceEffect): String {
            return collectToString(name, listOf(confusionChanceEffect.probability.toString(),
                confusionChanceEffect.effectDuration.toString()))
        }
    }
}