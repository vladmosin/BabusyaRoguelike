package inc.roguelike.babusya.element.interfaces

import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.abstracts.AbstractGameElement
import inc.roguelike.babusya.element.abstracts.AbstractStaticElement


/**
 * Game element which cannot move and does not have controller
 * */
interface StaticElement : GameElement {
    companion object {
        /**
         * Deserializes static element
         * */
        fun deserialize(string: String): StaticElement? {
            return AbstractStaticElement.deserialize(string)
        }
    }
}
