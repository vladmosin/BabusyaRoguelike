package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.map.Cell

/**
 * Creature is a game element, which can move and has characteristics
 * */
abstract class Creature(
    val creatureCharacteristics: CreatureCharacteristics,
    private var actionController: ActionController?,
    id: String,
    elementStatus: ElementStatus
) : GameElement(id, elementStatus) {

    open fun makeTurn() {
        if (actionController == null) {
            throw IllegalStateException("controller do not set")
        }
        actionController!!.makeTurn(this)
    }

    fun setActionController(controller: ActionController) {
        actionController = controller
    }

    companion object {
        fun deserialize(controllerFactory: ControllerFactory, string: String): Creature? {
            val deserializers = listOf { s: String -> Hero.deserialize(controllerFactory, s) }

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
