package inc.roguelike.babusya.map

interface CellObserver {
    fun onCellUpdate(cell: Cell)
}