package inc.roguelike.babusya.inputListeners

import InputListener
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.terminal.Terminal
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class ConsoleKeyboardListener(val terminal: Terminal): InputListener {

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

    override fun registerCommand(command: (InputData) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var job: Job? = null

    fun start() {
        job = GlobalScope.launch {
            while (true) {
                println("LOL")
            }
        }
    }

    fun stop() {
        runBlocking {
            job!!.cancelAndJoin()
        }
    }

}