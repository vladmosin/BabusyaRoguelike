package inc.roguelike.babusya.element.interfaces

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.abstracts.AbstractCreature

/**
 * Base interface for units which can move, such as monsters and heroes
 * Such units posses characteristics and action controller
 * */
interface Creature: GameElement {
    val characteristics: CreatureCharacteristics
    var actionController: ActionController?
    fun makeTurn(): Boolean

    companion object {
        fun deserialize(controllerFactory: ControllerFactory, string: String): Creature? {
            return AbstractCreature.deserialize(controllerFactory, string)
        }
    }

    override fun clone(): Creature
}
