package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.concrete.Wall
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.mocks.MockMap
import inc.roguelike.babusya.mocks.MockInputListener
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import java.util.*

class HeroActionControllerTest {

    private fun createCellWithElement(element: GameElement, map: MockMap): Cell {
        val cell = Cell()
        cell.storedItem = element
        map.mapElementToCell[element] = cell
        return cell
    }

    @Test
    fun testMakeTurnHeroToEmpty() {
        val inputListener = MockInputListener(LinkedList(listOf(InputData.LEFT)))
        val map = MockMap()
        val hero = Hero(
            actionController = HeroActionController(map, inputListener),
            creatureCharacteristics = CreatureCharacteristics.createBasic(),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h"
        )
        val heroCell = createCellWithElement(hero, map)
        val emptyCell = Cell()

        map.addNextLeft(emptyCell)
        hero.makeTurn()

        assertFalse(heroCell.storedItem.isActive())
        assertTrue(emptyCell.storedItem.isActive())
    }

    @Test
    fun testMakeMoveHeroToWall() {
        val inputListener = MockInputListener(LinkedList(listOf(InputData.LEFT)))
        val map = MockMap()
        val hero = Hero(
            actionController = HeroActionController(map, inputListener),
            creatureCharacteristics = CreatureCharacteristics.createBasic(),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h"
        )
        val heroCell = createCellWithElement(hero, map)
        val wallCell = createCellWithElement(Wall("w1", ElementStatus.ALIVE), map)

        map.addNextLeft(wallCell)
        hero.makeTurn()

        assertTrue(heroCell.storedItem.isActive())
        assertTrue(wallCell.storedItem.isActive())
    }
}
