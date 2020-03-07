package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.Visitor

/**
 * Implements stairs.
 * Using stairs creatures can move between floors.
 * */
class Stairs(id: String, elementStatus: ElementStatus) : StaticElement(id, elementStatus) {
    override fun <T> accept(visitor: Visitor<T>): T {
        return visitor.visitStairs(this)
    }

    override fun act(gameElement: GameElement) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bePunched(damage: Int) {}
}