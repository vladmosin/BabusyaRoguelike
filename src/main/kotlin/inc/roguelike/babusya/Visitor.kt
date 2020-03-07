package inc.roguelike.babusya

import inc.roguelike.babusya.gameElement.*

interface Visitor<T> {
    fun visitStairs(stairs: Stairs): T
    fun visitDoor(door: Door): T
    fun visitWall(wall: Wall): T
    fun visitLoot(loot: Loot): T

    fun visitHero(hero: Hero): T
    fun visitMonster(monster: Monster): T
}