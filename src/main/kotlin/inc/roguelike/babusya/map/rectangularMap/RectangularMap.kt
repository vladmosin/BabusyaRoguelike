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

/**
 * Implements simple rectangular game map.
 * Cells ordered in 2D rectangular
 * */
class RectangularMap(
    private val rectangle: Array<Array<Cell>>
) : GameMap {

    private val height = rectangle.size
    private val width = rectangle.get(0).size
    private val indexByCell = HashMap<Cell, Pair<Int, Int>>()
    private val cellByElement = HashMap<GameElement, Cell>()

    companion object {
        private const val name = "RectangularMap"

        /**
         * Creates RectangularMap from string
         * */
        fun deserialize(line: String, inputListener: InputListener): RectangularMap? {
            val name = getName(line)
            val args = getArguments(line)

            if (name == null || args == null || args.size < 2 || name != this.name) {
                return null
            }

            try {
                val height = args[0].toInt()
                val width = args[1].toInt()

                if (args.size != height * width + 2) {
                    return null
                }

                val map = RectangularMap(Array(height) { Array(width) { Cell() } })
                val controllerFactory = ControllerFactory(map, inputListener)

                for (i in 0 until height) {
                    for (j in 0 until width) {
                        val cell = Cell.deserialize(controllerFactory, args[2 + i * width + j])
                        if (cell == null) {
                            return null
                        } else {
                            map.rectangle[i][j].storedItem = cell.storedItem
                            map.rectangle[i][j].loot = cell.loot
                        }
                    }
                }

                map.updateControllers()

                return map

            } catch (e: NumberFormatException) {
                return null
            }
        }
    }

    private fun updateControllers() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                val item = rectangle[i][j].storedItem
                if (item is Creature) {
                    item.actionController?.changeGameMap(this)
                }

            }
        }
    }

    fun getRectangle(): Array<Array<Cell>> = rectangle

    private fun initIndexByCell() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                indexByCell[rectangle[i][j]] = Pair(i, j)
            }
        }
    }

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

    override fun positionOnScreen(cell: Cell): Pair<Int, Int> {
        return indexByCell[cell]!!
    }

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
    override fun serialize(): String {
        val args = ArrayList<String>()

        args.add(height.toString())
        args.add(width.toString())

        for (i in 0 until height) {
            for (j in 0 until width) {
                args.add(rectangle[i][j].serialize())
            }
        }

        return collectToString(name, args)
    }

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

    override fun cellByPosition(x: Int, y: Int): Cell? {
        return if (x < height && y < width) {
            rectangle[x][y]
        } else {
            null
        }
    }
}
