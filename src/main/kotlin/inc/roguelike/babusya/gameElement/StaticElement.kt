package inc.roguelike.babusya.gameElement

import InputListener
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.GameMap

/**
 * Base class for elements which cannot move
 * */
abstract class StaticElement(id: String, elementStatus: ElementStatus) : GameElement(id, elementStatus) {
    companion object {
        fun deserialize(string: String): StaticElement? {
            val deserializers = listOf(
                { s: String -> Wall.deserialize(s)},
                { s: String -> EmptyGameElement.deserialize(s)})

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
