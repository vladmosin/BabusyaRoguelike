package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.Visitor

class Wall: StaticElement {
    override fun <T> accept(visitor: Visitor<T>): T {
        return visitor.visitWall(this)
    }

    override fun act(gameElement: GameElement) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bePunched(damage: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}