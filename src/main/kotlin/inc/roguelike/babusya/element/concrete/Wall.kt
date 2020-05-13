package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.abstracts.AbstractStaticElement
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.visitors.ElementVisitor

/**
 * Implements wall.
 * Creatures cannot walk through the wall.
 * */
class Wall(id: String, elementStatus: ElementStatus) : AbstractStaticElement(id, elementStatus) {
    override fun <T> accept(visitor: ElementVisitor<T>): T {
        return visitor.visitWall(this)
    }

    override fun isActive(): Boolean {
        return elementStatus == ElementStatus.ALIVE
    }

    override fun serialize(): String {
        return collectToString(name, listOf(id, elementStatus.name))
    }

    override fun clone(): Wall {
        return Wall(id, elementStatus)
    }

    companion object {
        fun deserialize(string: String): Wall? {
            val name = getName(string)
            val args = getArguments(string)

            if (name == null || args == null || args.size != 2 || name != this.name) {
                return null
            }

            val status = ElementStatus.deserialize(args[1])
            return if (status == null) {
                null
            } else {
                Wall(args[0], status)
            }
        }

        private const val name = "Wall"
    }
}
