package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.gameElement.Creature
import inc.roguelike.babusya.gameElement.EmptyGameElement

abstract class ActionController(var cell: Cell) {
    companion object {
        private val controllers = mapOf<ControllerType, ActionController>(
            ControllerType.HeroController to HeroActionController()
        )

        fun getController(controllerType: ControllerType): ActionController? {
            return if (controllerType in controllers) {
                controllers[controllerType]
            } else {
                null
            }
        }
    }

    /**
     * Moves item from first cell to the second cell if possible, otherwise makes other necessary operations.
     * */
    fun makeMove(toCell: Cell) {
        if (cell == toCell) return

        val fromItem = cell.storedItem
        val toItem = toCell.storedItem

        assert(fromItem is Creature)

        val attack = (fromItem as Creature).getAttack()
        toItem.bePunched(attack)
        toItem.act(fromItem)

        if (!toItem.isActive() && fromItem.isActive()) {
            cell.storedItem = EmptyGameElement()
            toCell.storedItem = fromItem
            cell = toCell
        }

    }

    /**
     * Makes turn
     * */
    abstract fun makeTurn()
}