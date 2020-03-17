package inc.roguelike.babusya.map.rectangularMap

import inc.roguelike.babusya.gameElement.EmptyGameElement
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.GameMap


/**
 * Implements simple rectangular game map.
 * Cells ordered in 2D rectangular
 * */
class RectangularMap(private val height: Int, private val width: Int) : GameMap {

    companion object {
        fun loadFromFile(filePath: String): RectangularMap? {
            TODO("not implemented")
        }
    }

    private val rectangle = Array(height) { Array(width) { Cell(EmptyGameElement()) } }
    private val indexByCell = HashMap<Cell, Pair<Int, Int>>()

    fun getRectangle(): Array<Array<Cell>> = rectangle

    private fun initIndexByCell() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                indexByCell[rectangle[i][j]] = Pair(i, j)
            }
        }
    }

    init {
        initIndexByCell()
    }

    override fun positionOfCell(cell: Cell): Pair<Int, Int> {
        return indexByCell[cell]!!
    }

    override fun getLefterCell(cell: Cell): Cell? {
        val position = indexByCell[cell]
        assert(position != null)

        val (i, j) = position!!
        return if (j == 0) {
            null
        } else {
            rectangle[i][j - 1]
        }
    }

    override fun getRighterCell(cell: Cell): Cell? {
        val position = indexByCell[cell]
        assert(position != null)

        val (i, j) = position!!
        return if (j == width - 1) {
            null
        } else {
            rectangle[i][j + 1]
        }
    }

    override fun getUpperCell(cell: Cell): Cell? {
        val position = indexByCell[cell]
        assert(position != null)

        val (i, j) = position!!
        return if (i == 0) {
            null
        } else {
            rectangle[i - 1][j]
        }
    }

    override fun getDownerCell(cell: Cell): Cell? {
        val position = indexByCell[cell]
        assert(position != null)

        val (i, j) = position!!
        return if (i == height - 1) {
            null
        } else {
            rectangle[i + 1][j]
        }
    }

    override fun iterator(): Iterator<Cell> = object: Iterator<Cell> {
        var i = 0
        var j = 0

        override fun hasNext(): Boolean {
            return i < height
        }

        override fun next(): Cell {
            return rectangle[i][j].also {
                j++
                if (j == width) {
                    i++
                    j = 0
                }
            }
        }

    }
}
