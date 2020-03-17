package inc.roguelike.babusya.map.rectangularMap

import InputListener
import inc.roguelike.babusya.gameElement.*
import inc.roguelike.babusya.map.Cell
import kotlin.random.Random

class RectangularMapBuilder(
    private val height: Int,
    private val width: Int
) {
    private val rectangle = Array(height) { Array(width) { Cell(EmptyGameElement()) } }
    private var heroCell: Cell? = null

    fun buildMap(inputListener: InputListener): RectangularMap {
        val map = RectangularMap(rectangle)
        heroCell!!.storedItem.setController(heroCell!!, inputListener, map)
        return RectangularMap(rectangle)
    }

    private fun getEmptyPositions(): ArrayList<Pair<Int, Int>> {
        val emptyPositions = ArrayList<Pair<Int, Int>>()
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (!rectangle[i][j].storesActiveItem()) {
                    emptyPositions.add(Pair(i, j))
                }
            }
        }
        return emptyPositions
    }

    fun addHero(): RectangularMapBuilder {
        val emptyPositions = getEmptyPositions()
        val emptyCellsNumber = emptyPositions.size
        check(emptyCellsNumber > 0) { "There is no place for hero" }
        val (hi, hj) = emptyPositions[Random.nextInt(emptyCellsNumber)]

        val hero = Hero(
            actionController = null,
            creatureCharacteristics = CreatureCharacteristics.createBasic(),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h"
        )
        rectangle[hi][hj].storedItem = hero
        heroCell = rectangle[hi][hj]

        return this
    }

    fun addWalls(): RectangularMapBuilder {
        val emptyPositions = getEmptyPositions()
        emptyPositions.shuffle()
        val wallsNumber = emptyPositions.size / 4
        for (q in 0 until wallsNumber) {
            val (wi, wj) = emptyPositions[q]
            val wall = Wall("w${q+1}", ElementStatus.ALIVE)
            rectangle[wi][wj].storedItem = wall
        }
        return this
    }
}
