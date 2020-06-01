package inc.roguelike.babusya.effects

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.effects.memento.PunchEffectMemento
import inc.roguelike.babusya.element.*
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.concrete.Monster
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import java.lang.NumberFormatException
import kotlin.math.max

/**
 * Decreasing unit hp on given amount of damage effect
 * */
open class PunchEffect(val damage: Int): Effect {

    override fun visitHero(hero: Hero): Boolean {
        punchCreature(hero)
        return true
    }

    override fun visitMonster(monster: Monster): Boolean {
        punchCreature(monster)
        return true
    }

    override fun getDescription(from: GameElement?, to: GameElement?): String {
        val fromId = from?.id ?: "God"
        val toId = to?.id ?: "Nothing"
        return "Hit: $fromId --[$damage]--> $toId"
    }

    override fun serialize() = PunchEffectMemento.serialize(this)

    private fun punchCreature(creature: Creature) {
        creature.characteristics.hitPoints = max(0, creature.characteristics.hitPoints - damage)
        if (creature.characteristics.hitPoints == 0) {
            creature.elementStatus = ElementStatus.DEAD
        }
    }

    companion object {
        fun deserialize(line: String): PunchEffect? {
            return PunchEffectMemento.deserialize(line)
        }
    }
}
