package inc.roguelike.babusya.effects

import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.concrete.Monster
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import kotlin.math.min

class HealEffect(val healAmount: Int): Effect {
    override fun visitHero(hero: Hero): Boolean {
        healCreature(hero)
        return true
    }

    override fun visitMonster(monster: Monster): Boolean {
        healCreature(monster)
        return true
    }

    override fun getDescription(from: GameElement?, to: GameElement?): String {
        val fromId = from?.id ?: "God"
        val toId = to?.id ?: "Nothing"
        return "Heal: $fromId --[$healAmount]--> $toId"
    }

    private fun healCreature(creature: Creature) {
        creature.characteristics.hitPoints =
            min(creature.characteristics.maxHitPoints, creature.characteristics.hitPoints + healAmount)
    }
}
