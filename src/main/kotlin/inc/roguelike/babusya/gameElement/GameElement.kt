package inc.roguelike.babusya.gameElement

import InputListener
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.visitors.Visitor

/**
 * Basic class for all items on the game map
 * */
abstract class GameElement(val id: String, var elementStatus: ElementStatus) {
    /**
     * Calls appropriate visitor's method
     * */
    abstract fun <T> accept(visitor: Visitor<T>): T

    open fun makeTurn() {
        assert(false) // TODO somthing better?
    }

    /**
     * Performs action on another game element. Different behaviour for different types of game elements.
     * */
    abstract fun act(gameElement: GameElement)

    /**
     * Decreases GameElement's hit points. If hit points become less or equal than 0, game element dies
     * */
    abstract fun bePunched(damage: Int)

    /**
     * Checks that current game element is not empty and ALIVE
     * */
    abstract fun isActive(): Boolean

    /**
     * Converts game element to string
     * Doesn't contain || as a subsequence
     * */
    abstract fun serialize(): String

    companion object {
        fun deserialize(string: String): GameElement? {
            val deserializers = listOf(
                { s: String -> Creature.deserialize(s)},
                { s: String -> StaticElement.deserialize(s)})

            for (deserializer in deserializers) {
                val gameElement = deserializer(string)
                if (gameElement != null) {
                    return gameElement
                }
            }

            return null;
        }
    }

    abstract fun setController(cell: Cell, inputListener: InputListener, map: GameMap)
}