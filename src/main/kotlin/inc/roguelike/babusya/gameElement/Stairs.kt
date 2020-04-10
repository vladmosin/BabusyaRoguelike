package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.visitors.Visitor

/**
 * Implements stairs.
 * Using stairs creatures can move between floors.
 * */
class Stairs(id: String, elementStatus: ElementStatus) : StaticElement(id, elementStatus) {
    override fun <T> accept(visitor: Visitor<T>): T {
        return visitor.visitStairs(this)
    }


    override fun isActive(): Boolean {
        return elementStatus == ElementStatus.ALIVE
    }

    override fun serialize(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun deserialize(string: String): Stairs? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}