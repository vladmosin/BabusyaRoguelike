package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.ActionController
import inc.roguelike.babusya.Cell

abstract class Creature(val creatureCharacteristics: CreatureCharacteristics,
                        val actionController: ActionController,
                        id: String, elementStatus: ElementStatus) : GameElement(id, elementStatus) {
    abstract fun chooseMove(): Cell
}