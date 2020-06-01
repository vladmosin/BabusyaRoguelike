package inc.roguelike.babusya.visitors

import inc.roguelike.babusya.element.concrete.*

/**
 * Visitor pattern.
 * Game elements have method accept, which call appropriate visitor's method
 * */
interface ElementVisitor<T> {
    fun visitStairs(stairs: Stairs): T

    fun visitDoor(door: Door): T

    fun visitWall(wall: Wall): T

    fun visitEmptyGameElement(emptyGameElement: EmptyGameElement): T

    fun visitHero(hero: Hero): T

    fun visitMonster(monster: Monster): T

    fun visitConfused(decorableCreature: DecorableCreature): T
}
