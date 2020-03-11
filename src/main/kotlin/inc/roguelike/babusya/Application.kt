package inc.roguelike.babusya

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import inc.roguelike.babusya.UI.ConsoleRenderSystem
import inc.roguelike.babusya.inputListeners.ConsoleKeyboardListener
import inc.roguelike.babusya.levels.LevelCreator

fun main() {
    // TODO disable resize??
    val terminal = DefaultTerminalFactory()
        .setInitialTerminalSize(TerminalSize(100, 30))
        //.addTerminalEmulatorFrameAutoCloseTrigger() // TODO close game when frame closes
        .createTerminalEmulator()

    val renderSystem = ConsoleRenderSystem(terminal)
    val inputListener = ConsoleKeyboardListener(terminal)

    val levelCreator = LevelCreator()

    Game(renderSystem, inputListener, levelCreator).launch()

    renderSystem.close()
}