package inc.roguelike.babusya.element.abstracts

import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.EmptyGameElement
import inc.roguelike.babusya.element.concrete.Wall
import inc.roguelike.babusya.element.interfaces.StaticElement

/**
 * Implementation of basic methods for static game element
 * */
abstract class AbstractStaticElement(id: String, elementStatus: ElementStatus) :
    AbstractGameElement(id, elementStatus), StaticElement {
    companion object {
        /**
         * Deserializes static element
         * */
        fun deserialize(string: String): StaticElement? {
            val deserializers = listOf(
                { s: String -> Wall.deserialize(s) },
                { s: String -> EmptyGameElement.deserialize(s) }
            )

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
