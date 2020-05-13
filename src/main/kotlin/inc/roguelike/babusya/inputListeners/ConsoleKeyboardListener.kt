package inc.roguelike.babusya.inputListeners

import InputListener
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.terminal.Terminal
import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicInteger

/**
 * Receives input from console
 * Blocked by input
 * Works in separate thread
 */
class ConsoleKeyboardListener(val terminal: Terminal): AbstractInputListener() {
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

    public override suspend fun readInput(): InputData {
        var inputData: InputData?
        do {
            inputData = keyStrokeToInputData(terminal.readInput())
        } while (inputData == null)
        return inputData
    }
}
