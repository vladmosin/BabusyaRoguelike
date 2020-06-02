package inc.roguelike.babusya.effects

import inc.roguelike.babusya.effects.memento.ConfusionChanceEffectMemento
import inc.roguelike.babusya.element.concrete.DecorableCreature
import inc.roguelike.babusya.element.concrete.decorator.ConfuseDecorator
import inc.roguelike.babusya.element.interfaces.GameElement
import java.lang.Integer.max
import kotlin.random.Random

/**
 * Creature becomes confused with given probability when this effect applied
 * */
class ConfusionChanceEffect(val probability: Double, val effectDuration: Int): Effect {

    /**
     * Returns effect description
     * */
    override fun getDescription(from: GameElement?, to: GameElement?): String {
        return "Confusion: (from = " + (from?.id ?: "?") + ")" +
                "(to = " + (to?.id ?: "?") + ")"
    }

    /**
     * Serializes effect
     * */
    override fun serialize(): String {
        return ConfusionChanceEffectMemento.serialize(this)
    }

    /**
     * Visitor for confused
     * */
    override fun visitConfused(decorableCreature: DecorableCreature): Boolean {
        if (Random.nextDouble() < probability) {
            decorableCreature.decorator = ConfuseDecorator(decorableCreature.randomController)
            decorableCreature.moreStepsWhileConfused = max(decorableCreature.moreStepsWhileConfused, effectDuration)
            return true
        }
        return false
    }

    companion object {
        /**
         * Deserializes effect
         * */
        fun deserialize(line: String): ConfusionChanceEffect? {
            return ConfusionChanceEffectMemento.deserialize(line)
        }
    }
}
