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
import java.util.concurrent.atomic.AtomicReference

/**
 * Controller, which choose game to start and performs basic initialization
 * */
class OvertureController: Controller() {
    private var client: AtomicReference<Client> = AtomicReference()

    /**
     * Connects to server with given login, address and port
     * */
    fun connectToServer(login: String, address: String, port: Int) {
        client.set(Client(address, port, login))
    }

    /**
     * Disconnects from server
     * */
    fun disconnectFromServer() {
        // TODO
    }

    /**
     * Creates room
     * */
    fun createRoom(): Pair<Boolean, String> = client.get()?.createRoom() ?: Pair(false, "Error")

    /**
     * Returns list of rooms id
     * */
    fun getRooms(): List<Int> = client.get()?.getRooms()?.map { room -> room.id } ?: emptyList()

    /**
     * Joins room
     * */
    fun joinRoom(roomId: Int): Boolean = client.get()?.joinRoom(roomId) ?: false

    /**
     * Start multiplayer game
     * */
    fun startMultiplayerGame(roomId: Int) {
        println("Start multiplayer game with room_id = $roomId")

        val terminal = DefaultTerminalFactory()
            .setInitialTerminalSize(TerminalSize(100, 30))
            .createTerminalEmulator()

        val renderSystem = ConsoleRenderSystem(terminal)
        val inputListener = ConsoleKeyboardListener(terminal)

        fun receive(input: InputData) {
            println("Receive input data = ${input.name}")

            if (input == InputData.QUIT) {
                client.get()!!.leaveRoom(roomId)
            } else {
                client.get()!!.sendInputData(input.toString())
            }

            inputListener.addCommand { inputData -> receive(inputData) }
        }

        joinRoom(roomId)

        inputListener.start()
        inputListener.addCommand { input -> receive(input) }

        val gameOnClient = GameOnClient(renderSystem, client.get()!!, roomId)

        try {
            gameOnClient.launch()
        } finally {
            client.get()?.leaveRoom(roomId)
            renderSystem.close()
        }
    }

    /**
     * Start single player game
     * */
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
