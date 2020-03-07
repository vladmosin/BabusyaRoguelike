package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.Cell

interface Creature : GameElement {
    fun chooseMove(): Cell
}