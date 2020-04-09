package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.map.Cell

/**
 * Creature is a game element, which can move and has characteristics
 * */
abstract class Creature(
    val creatureCharacteristics: CreatureCharacteristics,
    protected var actionController: ActionController?,
    id: String,
    elementStatus: ElementStatus
) : GameElement(id, elementStatus) {


    open fun getAttack(): Int {
        return creatureCharacteristics.attack
    }

    open fun makeTurn() {
        if (actionController == null) {
            throw IllegalStateException("controller is not set")
        }
        actionController!!.makeTurn(this)
    }

    companion object {
        fun deserialize(string: String): Creature? {
            val deserializers = listOf { s: String -> Hero.deserialize(s) }

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
