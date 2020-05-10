package inc.roguelike.babusya.map

import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement

/**
 * Implements game map.
 * Stores order on cells.
 * */
interface GameMap : Iterable<Cell>, CellObserver {

    /**
     * Returns left neighbour if exists
     * */
    fun getLefterCell(cell: Cell): Cell?

    /**
     * Returns right neighbour if exists
     * */
    fun getRighterCell(cell: Cell): Cell?

    /**
     * Returns neighbour from above if exists
     * */
    fun getUpperCell(cell: Cell): Cell?

    /**
     * Returns neighbour from bottom if exists
     * */
    fun getDownerCell(cell: Cell): Cell?

    /**
     * Returns cell position on screen
     * */
    fun positionOnScreen(cell: Cell): Pair<Int, Int>

    /**
     * Returns cell by game element on it
     * */
    fun getCellByElement(gameElement: GameElement): Cell?

    /**
     * Transforms game map to string
     * */
    fun serialize(): String

    /**
     * Clones game map
     * */
    fun clone(): GameMap

    /**
     * Returns cell by position
     * */

    fun cellByPosition(x: Int, y: Int): Cell?

    /**
     * Takes som cell on the map and finds the width of the map
     */
    fun getWidth(cell: Cell): Int {
        var helper_cell = cell
        while (true) {
            val next_cell = getRighterCell(helper_cell)
            if (next_cell != null) {
                helper_cell = next_cell
            } else {
                break
            }
        }
        return positionOnScreen(helper_cell).second + 1
    }

    /**
     * Adds creature
     * */
    fun addCreature(creature: Creature)
}
