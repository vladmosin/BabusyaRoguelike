package inc.roguelike.babusya.map.rectangularMap

import InputListener
import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.map.memento.RectangularMapMemento

/**
 * Implements simple rectangular game map.
 * Cells ordered in 2D rectangular
 * */
class RectangularMap(
    private val rectangle: Array<Array<Cell>>
) : GameMap {

    val height = rectangle.size
    val width = rectangle.get(0).size
    private val indexByCell = HashMap<Cell, Pair<Int, Int>>()
    private val cellByElement = HashMap<GameElement, Cell>()

    companion object {
        /**
         * Creates RectangularMap from string
         * */
        fun deserialize(line: String, inputListener: InputListener): RectangularMap? {
            return RectangularMapMemento.deserialize(line, inputListener)
        }
    }

    /**
     * Updates controllers
     * */
    fun updateControllers() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                val item = rectangle[i][j].storedItem
                if (item is Creature) {
                    item.actionController?.changeGameMap(this)
                }

            }
        }
    }

    /**
     * Returns rectangle
     * */
    fun getRectangle(): Array<Array<Cell>> = rectangle

    private fun initIndexByCell() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                indexByCell[rectangle[i][j]] = Pair(i, j)
            }
        }
    }

    /**
     * Operation on cell update
     * */
    override fun onCellUpdate(cell: Cell) {
        cellByElement[cell.storedItem] = cell
    }

    init {
        initIndexByCell()
        for (cell in this) {
            onCellUpdate(cell)
            cell.attachObserver(this)
        }
    }

    /**
     * Returns position on screen
     * */
    override fun positionOnScreen(cell: Cell): Pair<Int, Int> {
        return indexByCell[cell]!!
    }

    /**
     * Returns cell by element
     * */
    override fun getCellByElement(gameElement: GameElement): Cell? {
        return cellByElement[gameElement]
    }


    /** Format:
     * RectangularMap
     * height width
     * _ .. _
     * _ .. _
     * ......
     * _ .. _
     *
     *
     * First line is a name of concrete GameMap implementation
     * Second line is board parameters (height and width)
     * Next height lines contains width number of serialized GameElements
     * */
    override fun serialize() = RectangularMapMemento.serialize(this)

    /**
     * Clones map
     * */
    override fun clone(): RectangularMap {
        val map = RectangularMap(Array(height) {Array(width) {Cell()}})
        for (i in 0 until height) {
            for (j in 0 until width) {
                map.rectangle[i][j] = rectangle[i][j].clone()
            }
        }

        map.updateControllers()
        return map
    }

    private fun getMovedCell(cell: Cell, di: Int, dj: Int): Cell? {
        val position = indexByCell[cell]
        assert(position != null)
        var (i, j) = position!!
        i += di
        j += dj
        return if (i in 0 until height && j in 0 until width) {
            rectangle[i][j]
        } else {
            null
        }
    }

    /**
     * Returns lefter cell
     * */
    override fun getLefterCell(cell: Cell): Cell? = getMovedCell(cell, 0, -1)

    /**
     * Returns righter cell
     * */
    override fun getRighterCell(cell: Cell): Cell? = getMovedCell(cell, 0, +1)

    /**
     * Returns upper cell
     * */
    override fun getUpperCell(cell: Cell): Cell? = getMovedCell(cell, -1, 0)

    /**
     * Returns downer cell
     * */
    override fun getDownerCell(cell: Cell): Cell? = getMovedCell(cell, +1, 0)

    /**
     * Returns iterator
     * */
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

    /**
     * Returns cell by its position
     * */
    override fun cellByPosition(x: Int, y: Int): Cell? {
        return if (x < height && y < width) {
            rectangle[x][y]
        } else {
            null
        }
    }
}
