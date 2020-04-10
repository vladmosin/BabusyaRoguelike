package inc.roguelike.babusya.UI

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.Terminal
import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.GameState
import inc.roguelike.babusya.levels.Level
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.visitors.ShowConsoleVisitor
import java.lang.Integer.min


/**
 * Renders console system
 * */
class ConsoleRenderSystem(val terminal: Terminal): RenderSystem {

    private val LEFT_FRAME = 30
    private val UP_FRAME = 10

    private val showVisitor = ShowConsoleVisitor()

    val screen: Screen = TerminalScreen(terminal)
    val textGraphics = screen.newTextGraphics()

    init {
        screen.cursorPosition = null
        screen.startScreen()
    }

    override fun render(level: Level, gameLog: GameLog) {
        textGraphics.fill(' ')
        textGraphics.foregroundColor = TextColor.ANSI.GREEN
        textGraphics.putString(LEFT_FRAME, UP_FRAME, level.getName())

        showMap(level.getMap(), LEFT_FRAME, UP_FRAME + 1)
        showLog(gameLog, 0, UP_FRAME, 10)
        screen.refresh()
    }

    private fun showLog(gameLog: GameLog, xOffset: Int, yOffset: Int, count: Int?) {
        textGraphics.foregroundColor = TextColor.ANSI.WHITE
        textGraphics.putString(xOffset, yOffset, "Log")
        val log = gameLog.last(count)
        for (i in log.indices) {
            textGraphics.putString(xOffset, yOffset + i + 1, log[i].take(LEFT_FRAME - 1))
        }
    }


    private fun showMap(map: GameMap, xOffset: Int, yOffset: Int) {
        for (cell in map) {
            val cellChar = cell.storedItem.accept(showVisitor)
            val (i, j) = map.positionOnScreen(cell)
            screen.setCharacter(j + xOffset, i + yOffset, TextCharacter(cellChar))
        }
    }

    fun close() {
        screen.stopScreen()
    }
}
