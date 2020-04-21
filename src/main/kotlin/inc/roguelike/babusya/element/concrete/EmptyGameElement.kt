package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.abstracts.AbstractStaticElement
import inc.roguelike.babusya.element.interfaces.GameElement
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
        return name
    }

    override fun clone(): EmptyGameElement {
        return EmptyGameElement()
    }

    companion object {
        fun deserialize(string: String): EmptyGameElement? {
            return if (string == name) {
                EmptyGameElement()
            } else {
                null
            }
        }

        private const val name = "e"
    }
}
