package inc.roguelike.babusya

/**
 * Implements game map.
 * Stores order on cells.
 * */
interface GameMap {
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
}