package inc.roguelike.babusya.UI

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.Terminal
import inc.roguelike.babusya.levels.Level
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.visitors.ShowConsoleVisitor


class ConsoleRenderSystem(val terminal: Terminal): RenderSystem {

    private val LEFT_FRAME = 20
    private val UP_FRAME = 10

    private val showVisitor = ShowConsoleVisitor()

    val screen: Screen = TerminalScreen(terminal)

    init {
        screen.cursorPosition = null
        screen.startScreen()
    }

    override fun render(level: Level) {
        val textGraphics = screen.newTextGraphics()
        textGraphics.foregroundColor = TextColor.ANSI.GREEN
        textGraphics.putString(LEFT_FRAME, UP_FRAME, level.getName())

        showMap(level.getMap())
        screen.refresh()
    }


    private fun showMap(map: GameMap) {
        for (cell in map) {
            val cellChar = cell.storedItem.accept(showVisitor)
            val (i, j) = map.positionOnScreen(cell)
            screen.setCharacter(j + LEFT_FRAME, i + UP_FRAME + 1, TextCharacter(cellChar))
        }
    }

    fun close() {
        screen.stopScreen()
    }
}