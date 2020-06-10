package inc.roguelike.babusya.element.interfaces

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.abstracts.AbstractCreature

/**
 * Base interface for units which can move, such as monsters and heroes
 * */
interface Creature: GameElement {
    val characteristics: CreatureCharacteristics
    var actionController: ActionController?

    /**
     * Makes turn
     * */
    fun makeTurn(): Boolean

    companion object {
        /**
         * Deserializes creature
         * */
        fun deserialize(controllerFactory: ControllerFactory, string: String): Creature? {
            return AbstractCreature.deserialize(controllerFactory, string)
        }
    }

    /**
     * Clones creature
     * */
    override fun clone(): Creature
}
