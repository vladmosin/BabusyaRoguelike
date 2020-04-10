package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.effects.PunchEffect
import inc.roguelike.babusya.map.Cell

/**
 * Creature is a game element, which can move and has characteristics
 * */
abstract class Creature(
    val characteristics: CreatureCharacteristics,
    var actionController: ActionController?,
    id: String,
    elementStatus: ElementStatus
) : GameElement(id, elementStatus) {

    open fun makeTurn() {
        if (actionController == null) {
            throw IllegalStateException("controller is not set")
        }
        actionController!!.makeTurn(this)
    }

    private fun getKickEffects(): List<Effect> {
        return listOf(PunchEffect(characteristics.attack))
    }

    override fun attackEffects(): List<Effect> {
        return getKickEffects()
    }

    override fun defensiveEffects(): List<Effect> {
        return getKickEffects()
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
