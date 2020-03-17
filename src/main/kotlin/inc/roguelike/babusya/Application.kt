package inc.roguelike.babusya

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import inc.roguelike.babusya.UI.ConsoleRenderSystem
import inc.roguelike.babusya.inputListeners.ConsoleKeyboardListener

/**
 * Initializes and starts game
 */
fun main() {
    val terminal = DefaultTerminalFactory()
        .setInitialTerminalSize(TerminalSize(100, 30))
        .createTerminalEmulator()

    val renderSystem = ConsoleRenderSystem(terminal)
    val inputListener = ConsoleKeyboardListener(terminal)
    inputListener.start()

    Game(renderSystem, inputListener).launch()

    renderSystem.close()
}
