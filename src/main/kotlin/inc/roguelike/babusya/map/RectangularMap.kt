package inc.roguelike.babusya.map

import InputListener
import inc.roguelike.babusya.controllers.HeroActionController
import inc.roguelike.babusya.gameElement.CreatureCharacteristics
import inc.roguelike.babusya.gameElement.ElementStatus
import inc.roguelike.babusya.gameElement.EmptyGameElement
import inc.roguelike.babusya.gameElement.Hero
import java.util.Random


/**
 * Implements simple rectangular game map.
 * Cells ordered in 2D rectangular
 * */
class RectangularMap(private val height: Int, private val width: Int):
    GameMap {
    companion object {
        fun loadFromFile(filePath: String): RectangularMap? {
            TODO("not implemented")
        }
    }

    private val map = Array(height) { Array(width) { Cell(EmptyGameElement()) } }
    private val indexByCell = HashMap<Cell, Pair<Int, Int>>()

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
