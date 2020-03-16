package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.controllers.ActionController

/**
 * Creature is a game element, which can move and has characteristics
 * */
abstract class Creature(val creatureCharacteristics: CreatureCharacteristics,
                        val actionController: ActionController,
                        id: String, elementStatus: ElementStatus) : GameElement(id, elementStatus) {

    /**
     * Moves creature
     * */
    abstract fun chooseMove(): Cell // Вроде лишнее, есть actionController

    open fun getAttack(): Int {
        return creatureCharacteristics.attack
    }

    companion object {
        fun deserialize(string: String): Creature? {
            val deserializers = listOf(
                { s: String -> Hero.deserialize(s)})

            for (deserializer in deserializers) {
                val gameElement = deserializer(string)
                if (gameElement != null) {
                    return gameElement
                }
            }

            return null;
        }
    }
}