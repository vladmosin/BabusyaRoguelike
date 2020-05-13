package inc.roguelike.babusya.map

/**
 * Interface for a concrete cell observer
 * */
interface CellObserver {
    /**
     * Performs logic after cell update
     * */
    fun onCellUpdate(cell: Cell)
}