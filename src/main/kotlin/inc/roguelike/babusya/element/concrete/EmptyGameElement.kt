package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.abstracts.AbstractStaticElement
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.visitors.ElementVisitor

/**
 * If cell doesn't store real game object, than it stores EmptyGameElement
 * */
class EmptyGameElement : AbstractStaticElement(
    name,
    ElementStatus.ALIVE
) {
    override fun <T> accept(visitor: ElementVisitor<T>): T {
        return visitor.visitEmptyGameElement(this)
    }

    override fun isActive(): Boolean {
        return false
    }

    override fun serialize(): String {
        return collectToString(name, listOf())
    }

    override fun clone(): EmptyGameElement {
        return EmptyGameElement()
    }

    companion object {
        fun deserialize(line: String): EmptyGameElement? {
            val name = getName(line)
            val args = getArguments(line)

            return if (args == null || name == null || args.isNotEmpty() || name != this.name) {
                null
            } else {
                EmptyGameElement()
            }
        }

        private const val name = "Empty"
    }
}
