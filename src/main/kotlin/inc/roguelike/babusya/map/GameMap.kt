package inc.roguelike.babusya.map

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

    private fun getSize(cell: Cell): Pair<Int, Int> {
        var helper_cell = cell
        while (true) {
            val next_cell = getRighterCell(helper_cell)
            if (next_cell != null) {
                helper_cell = next_cell
            } else {
                break
            }
        }
        val width = positionOnScreen(helper_cell).second + 1
        while (true) {
            val next_cell = getDownerCell(helper_cell)
            if (next_cell != null) {
                helper_cell = next_cell
            } else {
                break
            }
        }
        val height = positionOnScreen(helper_cell).first + 1
        return Pair(height, width)
    }

    /**
     * Takes som cell on the map and finds the width of the map
     */
    fun getWidth(cell: Cell): Int {
        return getSize(cell).second
    }

    /**
     * Takes som cell on the map and finds the height of the map
     */
    fun getHeight(cell: Cell): Int {
        return getSize(cell).first
    }
}
