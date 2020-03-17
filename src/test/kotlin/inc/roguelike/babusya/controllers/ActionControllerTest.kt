package inc.roguelike.babusya.controllers

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import inc.roguelike.babusya.UI.ConsoleRenderSystem
import inc.roguelike.babusya.gameElement.CreatureCharacteristics
import inc.roguelike.babusya.gameElement.ElementStatus
import inc.roguelike.babusya.gameElement.EmptyGameElement
import inc.roguelike.babusya.gameElement.Hero
import inc.roguelike.babusya.inputListeners.ConsoleKeyboardListener
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.GameMap
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

    override fun positionOfCell(cell: Cell): Pair<Int, Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun iterator(): Iterator<Cell> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class ActionControllerTest {

    private val terminal = DefaultTerminalFactory()
        .setInitialTerminalSize(TerminalSize(100, 30))
        .createTerminalEmulator()

    private val renderSystem = ConsoleRenderSystem(terminal)
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
    }
}
