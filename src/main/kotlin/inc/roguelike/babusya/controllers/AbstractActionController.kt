package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.gameElement.Creature
import inc.roguelike.babusya.gameElement.EmptyGameElement
import inc.roguelike.babusya.map.GameMap

abstract class AbstractActionController(val gameMap: GameMap): ActionController {
    /**
     * Moves item from first cell to the second cell if possible, otherwise makes other necessary operations.
     * */
    fun makeMove(creature: Creature, toCell: Cell) {
        val fromCell = gameMap.getCellByElement(creature)!!
        if (fromCell == toCell) return

        val toItem = toCell.storedItem

        if (!toItem.isActive() && creature.isActive()) {
            fromCell.storedItem = EmptyGameElement()
            toCell.storedItem = creature
        }
    }
}
