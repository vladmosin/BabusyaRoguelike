package inc.roguelike.babusya.gameElement

import InputListener
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.effects.Effect
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

    /**
     * Checks that current game element is not empty and ALIVE
     * */
    abstract fun isActive(): Boolean

    open fun attackEffects() : List<Effect> {
        return emptyList()
    }

    open fun defensiveEffects(): List<Effect> {
        return emptyList()
    }

    /**
     * Converts game element to string
     * Doesn't contain || as a subsequence
     * */
    abstract fun serialize(): String

    companion object {
        fun deserialize(controllerFactory: ControllerFactory, string: String): GameElement? {
            val deserializers = listOf(
                { s: String -> Creature.deserialize(controllerFactory, s)},
                { s: String -> StaticElement.deserialize(s)})

            for (deserializer in deserializers) {
                val gameElement = deserializer(string)
                if (gameElement != null) {
                    return gameElement
                }
            }

            return null
        }
    }
}
