package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.Cell
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
    abstract fun chooseMove(): Cell
}