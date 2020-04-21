package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.concrete.Monster
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.rectangularMap.RectangularMap
import inc.roguelike.babusya.mocks.MockInputListener
import inc.roguelike.babusya.mocks.MockMap
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test
import java.util.*

class MonsterControllersTest {

    private fun createCellWithElement(element: GameElement, map: MockMap): Cell {
        val cell = Cell()
        cell.storedItem = element
        map.mapElementToCell[element] = cell
        return cell
    }

    @Test
    fun testMakeFight() {
        val rectangular = Array(3) {Array(1) {Cell()} }
        val map = RectangularMap(rectangular)
        val passive = Hero(
            CreatureCharacteristics(10, 10, 1),
            PassiveController(map),
            "P",
            ElementStatus.ALIVE,
            0
        )
        val monsterAggressive = Monster(
            CreatureCharacteristics(10, 10, 10),
            AggressiveController(map, passive),
            "P",
            ElementStatus.ALIVE
        )
        rectangular[0][0].storedItem = monsterAggressive
        rectangular[2][0].storedItem = passive

        monsterAggressive.makeTurn()
        passive.makeTurn()
        assertEquals(rectangular[1][0], map.getCellByElement(monsterAggressive))
        assertEquals(rectangular[2][0], map.getCellByElement(passive))

        monsterAggressive.makeTurn()
        assertEquals(rectangular[2][0], map.getCellByElement(monsterAggressive))
        assertEquals(10, monsterAggressive.characteristics.hitPoints)
        assertEquals(ElementStatus.DEAD, passive.elementStatus)
    }
}
