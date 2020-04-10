package inc.roguelike.babusya.visitors

import inc.roguelike.babusya.gameElement.*

/**
 * Visitor, which shows elements to console
 * */
class ShowConsoleVisitor : Visitor<Char> {
    override fun visitStairs(stairs: Stairs): Char {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitDoor(door: Door): Char {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun visitWall(wall: Wall): Char {
        return '#'
    }

    override fun visitEmptyGameElement(emptyGameElement: EmptyGameElement): Char {
        return '.'
    }

    override fun visitHero(hero: Hero): Char {
        return '@'
    }

    override fun visitMonster(monster: Monster): Char {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
