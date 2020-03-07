package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.Cell
import inc.roguelike.babusya.Visitor

class Hero: Creature {
    override fun chooseMove(): Cell {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> accept(visitor: Visitor<T>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun act(gameElement: GameElement) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bePunched(damage: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}