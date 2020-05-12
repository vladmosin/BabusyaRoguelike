package inc.roguelike.babusya.effects

import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.element.concrete.*
import inc.roguelike.babusya.visitors.ElementVisitor

/**
 * Interface for effects which can be applied to gameElement
 *  Implements visitor on gameElements which returns whether
 *      the effect was successfully applied
 * */
interface Effect : ElementVisitor<Boolean> {
    override fun visitStairs(stairs: Stairs): Boolean = false
    override fun visitDoor(door: Door): Boolean = false
    override fun visitWall(wall: Wall): Boolean = false
    override fun visitEmptyGameElement(emptyGameElement: EmptyGameElement): Boolean = false
    override fun visitHero(hero: Hero): Boolean = false
    override fun visitMonster(monster: Monster): Boolean = false
    override fun visitConfused(confusableCreature: ConfusableCreature): Boolean = confusableCreature.creature.accept(this)

    /**
     * Applying effect to given game element
     * Returns whether the element was successfully applied
     * */
    fun apply(gameElement: GameElement): Boolean = gameElement.accept(this)

    /**
     * Returns description of effect applying which is supposed to be shown to player
     * */
    fun getDescription(from: GameElement?, to: GameElement?): String

    /**
     * Serializes effect
     * */
    fun serialize(): String

    companion object {
        fun deserialize(line: String): Effect? {
            val deserializers = listOf(
                { s: String -> ConfusionChanceEffect.deserialize(s) },
                { s: String -> HealEffect.deserialize(s) },
                { s: String -> MonsterPunchEffect.deserialize(s) },
                { s: String -> PunchEffect.deserialize(s) }

            )

            for (deserializer in deserializers) {
                val gameElement = deserializer(line)
                if (gameElement != null) {
                    return gameElement
                }
            }

            return null
        }
    }
}
