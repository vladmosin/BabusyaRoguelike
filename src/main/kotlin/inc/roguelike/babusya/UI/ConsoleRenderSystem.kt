package inc.roguelike.babusya.UI

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.Terminal
import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.levels.Level
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.visitors.ShowConsoleVisitor


/**
 * Renders console system
 * */
class ConsoleRenderSystem(terminal: Terminal): RenderSystem {

    private val LEFT_FRAME = 60
    private val UP_FRAME = 5
    private val LOG_ROWS = 15

    private val showVisitor = ShowConsoleVisitor()

    private val screen: Screen = TerminalScreen(terminal)
    private val textGraphics = screen.newTextGraphics()

    init {
        screen.cursorPosition = null
        screen.startScreen()
    }

    override fun render(level: Level, gameLog: GameLog) {
        textGraphics.fill(' ')
        textGraphics.foregroundColor = TextColor.ANSI.GREEN
        textGraphics.putString(LEFT_FRAME, UP_FRAME, level.getName())

        showMap(level.getMap(), LEFT_FRAME, UP_FRAME + 1)
        showLog(gameLog, 0, UP_FRAME, LOG_ROWS)
        screen.refresh()
    }

    private fun showLog(gameLog: GameLog, xOffset: Int, yOffset: Int, count: Int?) {
        textGraphics.foregroundColor = TextColor.ANSI.WHITE
        textGraphics.putString(xOffset, yOffset, "Log")
        val log = gameLog.last(count)
        for (i in log.indices) {
            val message = log[i].take(LEFT_FRAME - 1)
            if (message.isEmpty()) {
                continue
            }
            val separator: Int = message.indexOf(':')

            textGraphics.foregroundColor = TextColor.ANSI.RED
            val prefix = message.substring(0, separator)
            textGraphics.putString(xOffset, yOffset + i + 1, prefix)

            textGraphics.foregroundColor = TextColor.ANSI.GREEN
            textGraphics.putString(
                xOffset + 2 + prefix.length,
                yOffset + i + 1,
                message.substring(separator + 1)
            )
        }
    }

    private fun showMap(map: GameMap, xOffset: Int, yOffset: Int) {
        for (cell in map) {
            val (cellChar, cellColor) = cell.storedItem.accept(showVisitor)
            val (i, j) = map.positionOnScreen(cell)
            screen.setCharacter(j + xOffset, i + yOffset, TextCharacter(cellChar, cellColor, TextColor.ANSI.BLACK))
        }
    }

    /**
     * Closes render system
     * */
    fun close() {
        screen.stopScreen()
    }
}
