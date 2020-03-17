package inc.roguelike.babusya.controllers

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import inc.roguelike.babusya.gameElement.*
import inc.roguelike.babusya.inputListeners.ConsoleKeyboardListener
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.GameMap
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class EmptyMap : GameMap {
    override fun getLefterCell(cell: Cell): Cell? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRighterCell(cell: Cell): Cell? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUpperCell(cell: Cell): Cell? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDownerCell(cell: Cell): Cell? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun positionOnScreen(cell: Cell): Pair<Int, Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun serialize(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iterator(): Iterator<Cell> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class HeroActionControllerTest {

    private val terminal = DefaultTerminalFactory()
        .setInitialTerminalSize(TerminalSize(100, 30))
        .createTerminalEmulator()

    private val inputListener = ConsoleKeyboardListener(terminal)

    @Test
    fun testMakeMoveHeroToEmpty() {
        val hero = Hero(
            actionController = null,
            creatureCharacteristics = CreatureCharacteristics.createBasic(),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h"
        )
        val heroCell = Cell(hero)
        val emptyCell = Cell(EmptyGameElement())

        val heroActionController = HeroActionController(heroCell, inputListener, EmptyMap())

        heroActionController.makeMove(emptyCell)

        assertFalse(heroCell.storedItem.isActive())
        assertTrue(emptyCell.storedItem.isActive())
    }

    @Test
    fun testMakeMoveHeroToWall() {
        val hero = Hero(
            actionController = null,
            creatureCharacteristics = CreatureCharacteristics.createBasic(),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h"
        )
        val wall = Wall("w1", ElementStatus.ALIVE)
        val heroCell = Cell(hero)
        val wallCell = Cell(wall)

        val heroActionController = HeroActionController(heroCell, inputListener, EmptyMap())

        heroActionController.makeMove(wallCell)

        assertTrue(heroCell.storedItem.isActive())
        assertTrue(wallCell.storedItem.isActive())
    }
}
