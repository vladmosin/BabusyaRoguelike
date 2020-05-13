package inc.roguelike.babusya.mocks

import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.GameMap
import java.util.*
import kotlin.collections.HashMap

class MockMap: GameMap {

    override fun addCreature(creature: Creature) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val nextLeftCell: Queue<Cell?> = LinkedList()
    val nextRightCell: Queue<Cell?> = LinkedList()
    val nextUpperCell: Queue<Cell?> = LinkedList()
    val nextDownCell: Queue<Cell?> = LinkedList()

    val mapToPosition = HashMap<Cell, Pair<Int, Int>>()
    val mapElementToCell = HashMap<GameElement, Cell>()


    override fun getLefterCell(cell: Cell): Cell? {
        return nextLeftCell.poll()
    }

    override fun getRighterCell(cell: Cell): Cell? {
        return nextRightCell.poll()
    }

    override fun getUpperCell(cell: Cell): Cell? {
        return nextUpperCell.poll()
    }

    override fun getDownerCell(cell: Cell): Cell? {
        return nextDownCell.poll()
    }

    override fun positionOnScreen(cell: Cell): Pair<Int, Int> {
        return mapToPosition[cell]!!
    }

    override fun getCellByElement(gameElement: GameElement): Cell? {
        return mapElementToCell[gameElement]
    }

    override fun serialize(): String {
        TODO("Not yet implemented")
    }

    override fun clone(): GameMap {
        TODO("Not yet implemented")
    }

    override fun cellByPosition(x: Int, y: Int): Cell? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iterator(): Iterator<Cell> {
        TODO("Not yet implemented")
    }

    override fun onCellUpdate(cell: Cell) {
        TODO("Not yet implemented")
    }


    fun addNextLeft(cell: Cell): MockMap {
        nextLeftCell.add(cell)
        return this
    }

    fun addNextRight(cell: Cell): MockMap {
        nextRightCell.add(cell)
        return this
    }

    fun addNextDown(cell: Cell): MockMap {
        nextDownCell.add(cell)
        return this
    }

    fun addNextUpper(cell: Cell): MockMap {
        nextUpperCell.add(cell)
        return this
    }

}
