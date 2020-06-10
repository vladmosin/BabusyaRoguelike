package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.abstracts.AbstractStaticElement
import inc.roguelike.babusya.element.concrete.memento.WallMemento
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.visitors.ElementVisitor

/**
 * Implements wall.
 * Creatures cannot walk through the wall.
 * */
class Wall(id: String, elementStatus: ElementStatus) : AbstractStaticElement(id, elementStatus) {

    /**
     * Accept function for visitor
     * */
    override fun <T> accept(visitor: ElementVisitor<T>): T {
        return visitor.visitWall(this)
    }

    /**
     * Checks that wall is active
     * */
    override fun isActive(): Boolean {
        return elementStatus == ElementStatus.ALIVE
    }

    /**
     * Serializes wall
     * */
    override fun serialize(): String {
        return WallMemento.serialize(this)
    }

    /**
     * Clones wall
     * */
    override fun clone(): Wall {
        return Wall(id, elementStatus)
    }

    companion object {
        /**
         * Deserializes wall
         * */
        fun deserialize(string: String): Wall? {
            return WallMemento.deserialize(string)
        }
    }
}
