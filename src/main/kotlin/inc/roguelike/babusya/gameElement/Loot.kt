package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.Visitor

class Loot(id: String, elementStatus: ElementStatus) : StaticElement(id, elementStatus) {
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