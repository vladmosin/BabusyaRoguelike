package inc.roguelike.babusya.UI.overture

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import inc.roguelike.babusya.Game
import inc.roguelike.babusya.GameOnClient
import inc.roguelike.babusya.UI.ConsoleRenderSystem
import inc.roguelike.babusya.actionSystems.SinglePlayerActionSystem
import inc.roguelike.babusya.engines.SinglePlayerEngine
import inc.roguelike.babusya.inputListeners.ConsoleKeyboardListener
import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.levels.LevelInfo
import inc.roguelike.babusya.network.Client
import tornadofx.Controller

class OvertureController: Controller() {
    private var client: Client? = null


    fun connectToServer(login: String, address: String, port: Int) {
        client = Client(address, port, login)
    }

    fun disconnectFromServer() {
        // TODO
    }

    fun createRoom(roomId: Int): Pair<Boolean, String> {
        val status = client?.createRoom(roomId) ?: false
        val message = if (status) "OK" else "Error"
        return Pair(status, message)
    }

    fun getRooms(): List<Int> {
        return client?.getRooms()?.map { room -> room.id } ?: emptyList()
    }

    fun joinRoom(roomId: Int): Boolean {
        return client?.joinRoom(roomId) ?: false
    }

    fun startMultiplayerGame(roomId: Int) {
        val terminal = DefaultTerminalFactory()
            .setInitialTerminalSize(TerminalSize(100, 30))
            .createTerminalEmulator()

        val renderSystem = ConsoleRenderSystem(terminal)
        val inputListener = ConsoleKeyboardListener(terminal)

        fun receive(input: InputData) {
            client!!.sendInputData(input.toString())
            inputListener.addCommand { inputData -> receive(inputData) }
        }

        if (!joinRoom(roomId)) {
            createRoom(roomId)
        }

        inputListener.start()
        inputListener.addCommand { input -> receive(input) }

        GameOnClient(renderSystem, client!!).launch()
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
