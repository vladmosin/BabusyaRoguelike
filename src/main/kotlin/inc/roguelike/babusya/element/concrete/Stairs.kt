package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.abstracts.AbstractStaticElement
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.visitors.ElementVisitor

/**
 * Implements stairs.
 * Using stairs creatures can move between floors.
 * */
class Stairs(id: String, elementStatus: ElementStatus) : AbstractStaticElement(id, elementStatus) {
    override fun <T> accept(visitor: ElementVisitor<T>): T {
        return visitor.visitStairs(this)
    }


    override fun isActive(): Boolean {
        return elementStatus == ElementStatus.ALIVE
    }

    override fun serialize(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clone(): Stairs {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun deserialize(string: String): Stairs? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}
