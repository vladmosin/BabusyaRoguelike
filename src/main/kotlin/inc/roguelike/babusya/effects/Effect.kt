package inc.roguelike.babusya.effects

import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.element.concrete.*
import inc.roguelike.babusya.visitors.ElementVisitor

/**
 * Interface for effects implementing visitor pattern and able to affect game elements
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
     * */
    fun apply(gameElement: GameElement): Boolean = gameElement.accept(this)

    /**
     * Returns description
     * */
    fun getDescription(from: GameElement?, to: GameElement?): String
}
