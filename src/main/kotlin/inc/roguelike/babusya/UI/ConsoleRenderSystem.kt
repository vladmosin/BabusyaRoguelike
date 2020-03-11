package inc.roguelike.babusya.UI

import com.googlecode.lanterna.TextCharacter
import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.screen.Screen
import com.googlecode.lanterna.screen.TerminalScreen
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import inc.roguelike.babusya.levels.Level
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.visitors.ShowConsoleVisitor


class ConsoleRenderSystem: RenderSystem {

    private val showVisitor = ShowConsoleVisitor()

    val terminal = DefaultTerminalFactory().createTerminal()
    val screen: Screen = TerminalScreen(terminal)

    init {
        screen.cursorPosition = null
        screen.startScreen()
    }

    override fun render(level: Level) {
        val textGraphics = screen.newTextGraphics()
        textGraphics.foregroundColor = TextColor.ANSI.GREEN
        textGraphics.putString(0, 0, level.getName())

        showMap(level.getMap())
        screen.refresh()
    }


    private fun showMap(map: GameMap) {
        for (cell in map) {
            val cellChar = cell.storedItem.accept(showVisitor)
            val (i, j) = map.positionOfCell(cell)
            screen.setCharacter(j, i + 1, TextCharacter(cellChar))
        }
    }

    fun close() {
        screen.stopScreen()
    }
}