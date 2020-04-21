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

    fun positionOnScreen(cell: Cell): Pair<Int, Int>

    fun getCellByElement(gameElement: GameElement): Cell?

    fun serialize(): String

    fun clone(): GameMap
}
