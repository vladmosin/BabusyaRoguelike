package inc.roguelike.babusya.effects

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.concrete.Monster
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import java.lang.NumberFormatException
import kotlin.math.min

/**
 * Effect which successfully applies to creatures only
 * Creature restores {@param healAmount} hit points
 *  creature hit points remain bounded by creates max hit points
 */
class HealEffect(private val healAmount: Int): Effect {
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

    override fun serialize(): String {
        return collectToString(name, listOf(healAmount.toString()))
    }

    private fun healCreature(creature: Creature) {
        creature.characteristics.hitPoints =
            min(creature.characteristics.maxHitPoints, creature.characteristics.hitPoints + healAmount)
    }

    companion object {
        private const val name = "HealEffect"

        fun deserialize(line: String): HealEffect? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.name || args.size != 1) {
                null
            } else {
                try {
                    HealEffect(args[0].toInt())
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }
    }
}
