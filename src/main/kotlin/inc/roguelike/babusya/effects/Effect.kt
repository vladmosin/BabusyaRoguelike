package inc.roguelike.babusya.effects

import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.element.concrete.*
import inc.roguelike.babusya.visitors.ElementVisitor

interface Effect : ElementVisitor<Boolean> {
    override fun visitStairs(stairs: Stairs): Boolean = false
    override fun visitDoor(door: Door): Boolean = false
    override fun visitWall(wall: Wall): Boolean = false
    override fun visitEmptyGameElement(emptyGameElement: EmptyGameElement): Boolean = false
    override fun visitHero(hero: Hero): Boolean = false
    override fun visitMonster(monster: Monster): Boolean = false
    override fun visitConfused(confusableCreature: ConfusableCreature): Boolean = confusableCreature.creature.accept(this)

    fun apply(gameElement: GameElement): Boolean = gameElement.accept(this)

    fun getDescription(from: GameElement?, to: GameElement?): String
}
