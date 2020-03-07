package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.Cell

interface ActionController {
    /**
     * Moves item from first cell to the second cell if possible, otherwise makes other necessary operations.
     * */
    fun makeMove(fromCell: Cell, toCell: Cell) {
        TODO("not implemented")
    }

    /**
     * Makes turn
     * */
    fun makeTurn()
}