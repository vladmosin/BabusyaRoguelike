package inc.roguelike.babusya.element.interfaces

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.abstracts.AbstractCreature

interface Creature: GameElement {
    val characteristics: CreatureCharacteristics
    var actionController: ActionController?
    fun makeTurn()

    companion object {
        fun deserialize(controllerFactory: ControllerFactory, string: String): Creature? {
            return AbstractCreature.deserialize(controllerFactory, string)
        }
    }
}
