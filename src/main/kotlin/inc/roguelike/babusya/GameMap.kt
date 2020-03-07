package inc.roguelike.babusya

interface GameMap {
    fun getLefterCell(cell: Cell): Cell?
    fun getRighterCell(cell: Cell): Cell?
    fun getUpperCell(cell: Cell): Cell?
    fun getDownerCell(cell: Cell): Cell?
}