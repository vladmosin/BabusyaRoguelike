package inc.roguelike.babusya

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorAutoCloseTrigger
import inc.roguelike.babusya.UI.ConsoleRenderSystem
import inc.roguelike.babusya.inputListeners.ConsoleKeyboardListener
import inc.roguelike.babusya.levels.LevelCreator

fun main() {
    val terminal = DefaultTerminalFactory()
        .setInitialTerminalSize(TerminalSize(100, 30))
        .createTerminalEmulator()

    val renderSystem = ConsoleRenderSystem(terminal)
    val inputListener = ConsoleKeyboardListener(terminal)
    inputListener.start()

    val levelCreator = LevelCreator()

    Game(renderSystem, inputListener, levelCreator).launch()

    renderSystem.close()
}