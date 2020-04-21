package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.abstracts.AbstractStaticElement
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.visitors.ElementVisitor

class Door(id: String, elementStatus: ElementStatus) : AbstractStaticElement(id, elementStatus) {
    override fun <T> accept(visitor: ElementVisitor<T>): T {
        return visitor.visitDoor(this)
    }

    override fun isActive(): Boolean {
        return elementStatus == ElementStatus.ALIVE
    }

    override fun serialize(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clone(): GameElement {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun deserialize(string: String): Door? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        private const val name = "d"
    }
}
