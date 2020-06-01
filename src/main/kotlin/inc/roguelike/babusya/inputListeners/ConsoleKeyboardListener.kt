package inc.roguelike.babusya.inputListeners

import InputListener
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.terminal.Terminal
import inc.roguelike.babusya.commands.*
import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicInteger

/**
 * Receives input from console
 * Blocked by input
 * Works in separate thread
 */
class ConsoleKeyboardListener(val terminal: Terminal): InputListener {

    var commandId = AtomicInteger(0)
    val commandQueue = ConcurrentLinkedQueue<(AbstractCommand) -> Unit>()

    val inputDataToCommand: HashMap<InputData, AbstractCommand> = hashMapOf(
        InputData.RIGHT to RightCommand(),
        InputData.UP to UpCommand(),
        InputData.LEFT to LeftCommand(),
        InputData.DOWN to DownCommand(),
        InputData.INVENTORY_TOGGLE to ToggleInventoryCommand(),
        InputData.INVENTORY_DOWN to InventoryDownCommand(),
        InputData.INVENTORY_UP to InventoryUpCommand(),
        InputData.SAVE to SaveMapCommand()
    )

    private fun keyStrokeToInputData(keyStroke: KeyStroke?): InputData? {
        if (keyStroke == null) {
            return null
        }
        return when {
            keyStroke.character == 'k' -> InputData.INVENTORY_TOGGLE
            keyStroke.character == 'i' -> InputData.INVENTORY_UP
            keyStroke.character == 'j' -> InputData.INVENTORY_DOWN
            keyStroke.character == 's' -> InputData.SAVE
            else -> when (keyStroke.keyType) {
                KeyType.ArrowRight -> InputData.RIGHT
                KeyType.ArrowUp -> InputData.UP
                KeyType.ArrowLeft -> InputData.LEFT
                KeyType.ArrowDown -> InputData.DOWN
                else -> null
            }
        }
    }

    private fun readInput(): InputData {
        var inputData: InputData?
        do {
            inputData = keyStrokeToInputData(terminal.readInput())
        } while (inputData == null)
        return inputData
    }

    /**
     * Adds command.
     * Received input processed by commands in order of registration
     * Returns unique command identification
     */
    override fun addCommand(command: (AbstractCommand) -> Unit): Int {
        val id = commandId.getAndIncrement()
        commandQueue.add(command)
        return id
    }


    var job: Job? = null

    /**
     * Starts receiving input
     */
    fun start() {
        job = GlobalScope.launch {
            while (true) {
                val inputData = readInput()
                val curOperations = commandQueue.size

                for (i in 0 until curOperations) {
                    val command = commandQueue.poll()
                    command(inputDataToCommand[inputData]!!)
                }
            }
        }
    }

    fun stop() {
        runBlocking {
            job!!.cancelAndJoin()
        }
    }

}
