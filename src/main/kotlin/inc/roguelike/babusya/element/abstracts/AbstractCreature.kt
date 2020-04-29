package inc.roguelike.babusya.element.abstracts

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.ConfusableCreature
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.concrete.Monster
import inc.roguelike.babusya.element.interfaces.Creature

/**
 * Implementation of common methods for creature
 * */
abstract class AbstractCreature(
    override val characteristics: CreatureCharacteristics,
    override var actionController: ActionController?,
    id: String,
    elementStatus: ElementStatus
) : AbstractGameElement(id, elementStatus), Creature {

    override fun makeTurn(): Boolean {
        if (actionController == null) {
            throw IllegalStateException("controller is not set")
        }
        return actionController!!.makeTurn(this)
    }

    companion object {
        fun deserialize(controllerFactory: ControllerFactory, string: String): Creature? {
            val deserializers = listOf(
                { s: String -> Hero.deserialize(controllerFactory, s) },
                { s: String -> Monster.deserialize(controllerFactory, s) },
                { s: String -> ConfusableCreature.deserialize(controllerFactory, s) }
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
