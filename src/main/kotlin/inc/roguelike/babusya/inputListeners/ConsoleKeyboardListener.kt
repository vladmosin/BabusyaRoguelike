package inc.roguelike.babusya.inputListeners

import InputListener
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.terminal.Terminal
import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

/**
 * Receives input from console
 * Blocked by input
 * Works in separate thread
 */
class ConsoleKeyboardListener(val terminal: Terminal): InputListener {

    var commandId = AtomicInteger(0)
    val commandMap = ConcurrentHashMap<Int, (InputData) -> Unit>()

    private fun keyStrokeToInputData(keyStroke: KeyStroke?): InputData? {
        return when(keyStroke?.keyType) {
            KeyType.ArrowRight -> InputData.RIGHT
            KeyType.ArrowUp -> InputData.UP
            KeyType.ArrowLeft -> InputData.LEFT
            KeyType.ArrowDown -> InputData.DOWN
            else -> null
        }
    }

    private fun readInput(): InputData {
        var inputData: InputData? = null
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
    override fun addCommand(command: (InputData) -> Unit): Int {
        val id = commandId.getAndIncrement()
        commandMap[id] = command
        return id
    }


    /**
     * Removes command by id
     */
    override fun removeCommand(id: Int) {
        commandMap.remove(id)
    }

    var job: Job? = null

    /**
     * Starts receiving input
     */
    fun start() {
        job = GlobalScope.launch {
            while (true) {
                val inputData = readInput()
                for ((_, command) in commandMap) {
                    command(inputData)
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