package inc.roguelike.babusya.UI.overture

import InputListener
import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import inc.roguelike.babusya.Game
import inc.roguelike.babusya.GameOnClient
import inc.roguelike.babusya.UI.ConsoleRenderSystem
import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.actionSystems.SinglePlayerActionSystem
import inc.roguelike.babusya.engines.SinglePlayerEngine
import inc.roguelike.babusya.inputListeners.ConsoleKeyboardListener
import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.levels.LevelInfo
import inc.roguelike.babusya.network.Client
import kotlinx.coroutines.runBlocking
import tornadofx.Controller

class OvertureController: Controller() {


    fun connectToServer(login: String, address: String, port: Int) {
    }

    fun disconnectFromServer() {

    }

    fun createRoom(client: Client): Pair<Boolean, String> {
        return Pair(false, "Server not implemented")
    }

    fun getRooms(): List<Int> {
        return IntRange(1, 30).toList()
    }

    fun joinRoom(roomId: Int, client: Client): Boolean {
        TODO()
    }

    fun startMultiplayerGame(roomId: Int, client: Client) {
        val terminal = DefaultTerminalFactory()
            .setInitialTerminalSize(TerminalSize(100, 30))
            .createTerminalEmulator()

        val renderSystem = ConsoleRenderSystem(terminal)
        val inputListener = ConsoleKeyboardListener(terminal)

        fun receive(input: InputData) {
            client.sendInputData(input.name)
            inputListener.addCommand { inputData -> receive(inputData) }
        }

        if (!joinRoom(roomId, client)) {
            createRoom(client)
        }

        inputListener.start()
        inputListener.addCommand { input -> receive(input) }

        GameOnClient(renderSystem, client).launch()
        renderSystem.close()
    }

    fun startSinglePlayerGame(levelInfo: LevelInfo) {
        val terminal = DefaultTerminalFactory()
            .setInitialTerminalSize(TerminalSize(100, 30))
            .createTerminalEmulator()

        val renderSystem = ConsoleRenderSystem(terminal)
        val inputListener = ConsoleKeyboardListener(terminal)
        inputListener.start()

        Game(inputListener,
            SinglePlayerEngine(renderSystem, SinglePlayerActionSystem()),
            levelInfo).launch()

        renderSystem.close()
    }
}
