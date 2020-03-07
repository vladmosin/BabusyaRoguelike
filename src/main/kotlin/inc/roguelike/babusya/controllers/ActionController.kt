package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.Cell
import inc.roguelike.babusya.gameElement.Creature

interface ActionController {
    /**
     * Moves item from first cell to the second cell if possible, otherwise makes other necessary operations.
     * */
    fun makeMove(fromCell: Cell, toCell: Cell) {
        val fromItem = fromCell.storedItem
        val toItem = toCell.storedItem

        assert(fromItem is Creature)

        val attack = (fromItem as Creature).getAttack()
        toItem.bePunched(attack)
        toItem.act(fromItem)

        if (!toItem.isActive() && fromItem.isActive()) {
            toCell.storedItem = fromItem
        }
    }

    /**
     * Makes turn
     * */
    fun makeTurn()
}