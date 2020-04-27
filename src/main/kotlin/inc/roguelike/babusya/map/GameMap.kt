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
}
