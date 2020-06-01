package inc.roguelike.babusya.visitors

import inc.roguelike.babusya.element.concrete.*

/**
 * Visitor pattern.
 * Game elements have method accept, which call appropriate visitor's method
 * */
interface ElementVisitor<T> {
    /**
     * Visits wall
     * */
    fun visitWall(wall: Wall): T

    /**
     * Visits empty game element
     * */
    fun visitEmptyGameElement(emptyGameElement: EmptyGameElement): T

    /**
     * Visits hero
     * */
    fun visitHero(hero: Hero): T

    /**
     * Visits monster
     * */
    fun visitMonster(monster: Monster): T

    /**
     * Visits confused
     * */
    fun visitConfused(decorableCreature: DecorableCreature): T
}
