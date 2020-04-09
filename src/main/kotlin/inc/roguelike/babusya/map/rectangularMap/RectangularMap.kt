package inc.roguelike.babusya.map.rectangularMap

import InputListener
import inc.roguelike.babusya.gameElement.EmptyGameElement
import inc.roguelike.babusya.gameElement.GameElement
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
        /**
         * Creates RectangularMap from string
         * */
        fun deserialize(string: String, inputListener: InputListener): RectangularMap? {
            val parts = string.split("\n")
            if (parts.size < 2 || parts[0] != name) {
                return null
            }

            val sizes = parseHeightWidth(parts[1]) ?: return null
            val height = sizes.first
            val width = sizes.second
            val map = RectangularMap(Array(height) {Array(width) {Cell()}})

            val gameElements = parseMap(parts) ?: return null

            for (i in 0 until height) {
                for (j in 0 until width) {
                    gameElements[i][j].setController(map.rectangle[i][j], inputListener, map)
                    map.rectangle[i][j].storedItem = gameElements[i][j]
                }
            }

            return map
        }

        private fun parseMap(parts: List<String>): List<List<GameElement>>? {
            val sizes = parseHeightWidth(parts[1]) ?: return null
            val height = sizes.first
            val width = sizes.second
            val gameElements = ArrayList<List<GameElement>>()

            if (parts.size != height + 2) {
                return null
            }

            for (i in 2..height + 1) {
                val items = parseRow(parts[i], width) ?: return null
                gameElements.add(items)
            }

            return gameElements
        }

        private fun parseRow(string: String, width: Int): List<GameElement>? {
            val parts = string.split("&")
            if (parts.size != width) {
                return null
            }

            val items = ArrayList<GameElement>()
            for (part in parts) {
                val item = GameElement.deserialize(part) ?: return null
                items.add(item)
            }

            return items
        }

        private fun parseHeightWidth(string: String): Pair<Int, Int>? {
            val parts = string.split(" ")
            return if (parts.size != 2) {
                null
            } else {
                try {
                    val height = parts[0].toInt()
                    val width = parts[1].toInt()

                    Pair(height, width)
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }

        private const val name = "RectangularMap"
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
        val builder = StringBuilder("${name}\n${height} ${width}\n")
        for (i in 0 until height) {
            for (j in 0 until width) {
                builder.append(rectangle[i][j].serialize())
                if (j < width - 1) {
                    builder.append('&')
                }
            }

            if (i < height - 1) {
                builder.append('\n')
            }
        }

        return builder.toString()
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
