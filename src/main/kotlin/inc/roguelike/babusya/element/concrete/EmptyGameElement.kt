package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.abstracts.AbstractStaticElement
import inc.roguelike.babusya.element.concrete.memento.EmptyGameElementMemento
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.visitors.ElementVisitor

/**
 * If cell doesn't store real game object, than it stores EmptyGameElement
 * */
class EmptyGameElement : AbstractStaticElement(
    EmptyGameElementMemento.name,
    ElementStatus.ALIVE
) {
    /**
     * Accept function for visitor
     * */
    override fun <T> accept(visitor: ElementVisitor<T>): T {
        return visitor.visitEmptyGameElement(this)
    }

    /**
     * Checks that element is active
     * */
    override fun isActive(): Boolean {
        return false
    }

    /**
     * Serializes game element
     * */
    override fun serialize(): String {
        return EmptyGameElementMemento.serialize(this)
    }

    /**
     * Clones element
     * */
    override fun clone(): EmptyGameElement {
        return EmptyGameElement()
    }

    companion object {
        /**
         * Deserializes element
         * */
        fun deserialize(line: String): EmptyGameElement? {
            return EmptyGameElementMemento.deserialize(line)
        }
    }
}
