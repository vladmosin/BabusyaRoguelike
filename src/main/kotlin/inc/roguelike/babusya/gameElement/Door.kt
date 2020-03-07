package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.Visitor

class Door(id: String, elementStatus: ElementStatus) : StaticElement(id, elementStatus) {
    override fun <T> accept(visitor: Visitor<T>): T {
        return visitor.visitDoor(this)
    }

    override fun act(gameElement: GameElement) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bePunched(damage: Int) {}

    override fun isActive(): Boolean {
        return elementStatus == ElementStatus.ALIVE
    }
}