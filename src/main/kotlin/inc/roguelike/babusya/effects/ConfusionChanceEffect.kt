package inc.roguelike.babusya.effects

import inc.roguelike.babusya.effects.memento.ConfusionChanceEffectMemento
import inc.roguelike.babusya.element.concrete.DecorableCreature
import inc.roguelike.babusya.element.interfaces.GameElement
import java.lang.Integer.max
import kotlin.random.Random

/**
 * Creature becomes confused with given probability when this effect applied
 * */
class ConfusionChanceEffect(val probability: Double, val effectDuration: Int): Effect {
    override fun getDescription(from: GameElement?, to: GameElement?): String {
        return "Confusion: (from = " + (from?.id ?: "?") + ")" +
                "(to = " + (to?.id ?: "?") + ")"
    }

    override fun serialize(): String {
        return ConfusionChanceEffectMemento.serialize(this)
    }

    override fun visitConfused(decorableCreature: DecorableCreature): Boolean {
        if (Random.nextDouble() < probability) {
            decorableCreature.moreStepsWhileConfused = max(decorableCreature.moreStepsWhileConfused, effectDuration)
            return true
        }
        return false
    }

    companion object {
        fun deserialize(line: String): ConfusionChanceEffect? {
            return ConfusionChanceEffectMemento.deserialize(line)
        }
    }
}
