package inc.roguelike.babusya

import inc.roguelike.babusya.controllers.HeroActionController
import inc.roguelike.babusya.gameElement.CreatureCharacteristics
import inc.roguelike.babusya.gameElement.ElementStatus
import inc.roguelike.babusya.gameElement.EmptyGameElement
import inc.roguelike.babusya.gameElement.Hero

class RectangularMap(private val height: Int, private val width: Int): GameMap {
    companion object {
        fun loadFromFile(filePath: String): RectangularMap? {
            TODO("not implemented")
        }
    }

    private val map = Array(height) { Array(width) {Cell(EmptyGameElement())} }
    private val indexByCell = HashMap<Cell, Pair<Int, Int>>()

    init {
        generateMap()
    }

    private fun generateMap() {
        val actionController = HeroActionController()
        map[0][0].storedItem = Hero(
            actionController = actionController,
            creatureCharacteristics = CreatureCharacteristics.createBasic(),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h")
    }

    private fun initIndexByCell() {
        for (i in 0..height) {
            for (j in 0..width) {
                indexByCell[map[i][j]] = Pair(i, j)
            }
        }
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
}