package inc.roguelike.babusya.effects

import inc.roguelike.babusya.gameElement.Creature
import inc.roguelike.babusya.gameElement.ElementStatus
import inc.roguelike.babusya.gameElement.GameElement
import kotlin.math.max

class PunchEffect(val damage: Int): Effect {

    override fun apply(gameElement: GameElement) {
        when (gameElement) {
            is Creature -> punchCreature(gameElement)
        }
    }

    override fun getDescription(from: GameElement?, to: GameElement?): String? {
        if (to !is Creature) return null
        val fromId = from?.id ?: "God"
        val toId = to.id
        return "$fromId hits $toId by $damage"
    }

    private fun punchCreature(creature: Creature) {
        creature.characteristics.hitPoints = max(0, creature.characteristics.hitPoints - damage)
        if (creature.characteristics.hitPoints == 0) {
            creature.elementStatus = ElementStatus.DEAD
        }
    }
}