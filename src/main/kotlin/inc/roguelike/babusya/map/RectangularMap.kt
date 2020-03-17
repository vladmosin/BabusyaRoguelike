package inc.roguelike.babusya.map

import InputListener
import inc.roguelike.babusya.controllers.HeroActionController
import inc.roguelike.babusya.gameElement.*
import java.util.Random


/**
 * Implements simple rectangular game map.
 * Cells ordered in 2D rectangular
 * */
class RectangularMap(private val height: Int, private val width: Int): GameMap {
    private val map = Array(height) { Array(width) { Cell(EmptyGameElement()) } }
    private val indexByCell = HashMap<Cell, Pair<Int, Int>>()

    companion object {
        fun deserialize(string: String): RectangularMap? {
            val parts = string.split("\n")
            if (parts.size < 2 || parts[0] != name) {
                return null
            }

            val sizes = parseHeightWidth(parts[1]) ?: return null
            val gameElements = parseMap(parts) ?: return null
            val height = sizes.first
            val width = sizes.second

            val map = RectangularMap(height, width)
            for (i in 0..height) {
                for (j in 0..width) {
                    map.map[i][j].storedItem = gameElements[i][j]
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

            for (i in 2..height + 2) {
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

    private fun initIndexByCell() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                indexByCell[map[i][j]] = Pair(i, j)
            }
        }
    }

    init {
        initIndexByCell()
    }

    override fun positionOfCell(cell: Cell): Pair<Int, Int> {
        return indexByCell[cell]!!
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
        for (i in 0..height) {
            for (j in 0..width) {
                builder.append(map[i][j].serialize())
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

    // TODO move to level creator? probably builder will be useful
    // At least for creating gameElements or Characteristics
    // More patterns!!! :)
    fun generateMap(inputListener: InputListener) {
        val random = Random()
        val i = random.nextInt(height)
        val j = random.nextInt(width)
        val actionController = HeroActionController(map[i][j], inputListener, this)

        map[i][j].storedItem = Hero(
            actionController = actionController,
            creatureCharacteristics = CreatureCharacteristics.createBasic(),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h")
    }

    override fun getLefterCell(cell: Cell): Cell? {
        val position = indexByCell[cell]
        assert(position != null)

        val (i, j) = position!!
        return if (j == 0) {
            null
        } else {
            map[i][j - 1]
        }
    }

    override fun getRighterCell(cell: Cell): Cell? {
        val position = indexByCell[cell]
        assert(position != null)

        val (i, j) = position!!
        return if (j == width - 1) {
            null
        } else {
            map[i][j + 1]
        }
    }

    override fun getUpperCell(cell: Cell): Cell? {
        val position = indexByCell[cell]
        assert(position != null)

        val (i, j) = position!!
        return if (i == 0) {
            null
        } else {
            map[i - 1][j]
        }
    }

    override fun getDownerCell(cell: Cell): Cell? {
        val position = indexByCell[cell]
        assert(position != null)

        val (i, j) = position!!
        return if (i == height - 1) {
            null
        } else {
            map[i + 1][j]
        }
    }

    override fun iterator(): Iterator<Cell> = object: Iterator<Cell> {
        var i = 0
        var j = 0

        override fun hasNext(): Boolean {
            return i < height
        }

        override fun next(): Cell {
            return map[i][j].also {
                j++
                if (j == width) {
                    i++
                    j = 0
                }
            }
        }

    }
}