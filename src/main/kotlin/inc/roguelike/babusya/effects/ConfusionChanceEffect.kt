package inc.roguelike.babusya.effects

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.concrete.ConfusableCreature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import java.lang.Integer.max
import java.lang.NumberFormatException
import kotlin.random.Random

/**
 * Creature becomes confused with given probability when this effect applied
 * Effects lasts for {@param effectDuration} turns
 * Applies only to ConfusableCreature element
 * */
class ConfusionChanceEffect(private val probability: Double, private val effectDuration: Int): Effect {
    override fun getDescription(from: GameElement?, to: GameElement?): String {
        return "Confusion: (from = " + (from?.id ?: "?") + ")" +
                "(to = " + (to?.id ?: "?") + ")"
    }

    override fun serialize(): String {
        return collectToString(name, listOf(probability.toString(), effectDuration.toString()))
    }

    override fun visitConfused(confusableCreature: ConfusableCreature): Boolean {
        if (Random.nextDouble() < probability) {
            confusableCreature.moreStepsWhileConfused = max(confusableCreature.moreStepsWhileConfused, effectDuration)
            return true
        }
        return false
    }

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
    }
}
