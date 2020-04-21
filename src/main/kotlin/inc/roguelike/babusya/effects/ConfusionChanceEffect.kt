package inc.roguelike.babusya.effects

import inc.roguelike.babusya.element.concrete.ConfusableCreature
import inc.roguelike.babusya.element.interfaces.GameElement
import java.lang.Integer.max
import kotlin.random.Random

class ConfusionChanceEffect(val probability: Double, val effectDuration: Int): Effect {
    override fun getDescription(from: GameElement?, to: GameElement?): String {
        return "Confusion: (from = " + (from?.id ?: "?") + ")" +
                "(to = " + (to?.id ?: "?") + ")"
    }

    override fun visitConfused(confusableCreature: ConfusableCreature): Boolean {
        if (Random.nextDouble() < probability) {
            confusableCreature.moreStepsWhileConfused = max(confusableCreature.moreStepsWhileConfused, effectDuration)
            return true
        }
        return false
    }
}
