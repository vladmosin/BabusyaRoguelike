package inc.roguelike.babusya.map.rectangularMap

import InputListener
import inc.roguelike.babusya.controllers.HeroActionController
import inc.roguelike.babusya.gameElement.CreatureCharacteristics
import inc.roguelike.babusya.gameElement.ElementStatus
import inc.roguelike.babusya.gameElement.Hero
import org.w3c.dom.css.Rect
import kotlin.random.Random

class RectangularMapBuilder(
    private val height: Int,
    private val width: Int
) {
    private val map = RectangularMap(height, width)

    fun buildMap() = map

    fun addHero(inputListener: InputListener): RectangularMapBuilder {
        val emptyPositions = ArrayList<Pair<Int, Int>>()
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (!map.getRectangle()[i][j].storesActiveItem()) {
                    emptyPositions.add(Pair(i, j))
                }
            }
        }
        val emptyCellsNumber = emptyPositions.size
        check(emptyCellsNumber > 0) { "There is no place for hero" }
        val (hi, hj) = emptyPositions[Random.nextInt(emptyCellsNumber)]

        val actionController = HeroActionController(map.getRectangle()[hi][hj], inputListener, map)
        val hero = Hero(
            actionController = actionController,
            creatureCharacteristics = CreatureCharacteristics.createBasic(),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h"
        )


        map.getRectangle()[hi][hj].storedItem = hero

        return this
    }
}
