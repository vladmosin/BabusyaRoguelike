package inc.roguelike.babusya.UI.overture

import InputListener
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import inc.roguelike.babusya.Game
import inc.roguelike.babusya.UI.ConsoleRenderSystem
import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.inputListeners.ConsoleKeyboardListener
import inc.roguelike.babusya.levels.LevelInfo
import tornadofx.Controller

class OvertureController: Controller() {

    fun connectToServer(address: String, port: Int) {
        TODO()
    }

    fun disconnectFromServer() {
        TODO()
    }

    fun getRooms() {
        TODO()
    }

    fun startGame(levelInfo: LevelInfo) {
        val terminal = DefaultTerminalFactory()
            .setInitialTerminalSize(TerminalSize(100, 30))
            .createTerminalEmulator()

        val renderSystem = ConsoleRenderSystem(terminal)
        val inputListener = ConsoleKeyboardListener(terminal)
        inputListener.start()

        Game(renderSystem, inputListener, levelInfo).launch()

        renderSystem.close()
    }
}
