package inc.roguelike.babusya

import inc.roguelike.babusya.gameElement.*

/**
 * Visitor pattern.
 * Game elements have method accept, which call appropriate visitor's method
 * */
interface Visitor<T> {
    fun visitStairs(stairs: Stairs): T
    fun visitDoor(door: Door): T
    fun visitWall(wall: Wall): T
    fun visitLoot(loot: Loot): T
    fun visitEmptyGameElement(emptyGameElement: EmptyGameElement): T

    fun visitHero(hero: Hero): T
    fun visitMonster(monster: Monster): T
}