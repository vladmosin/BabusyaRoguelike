package inc.roguelike.babusya.inputListeners

import InputListener
import com.googlecode.lanterna.input.KeyStroke
import com.googlecode.lanterna.input.KeyType
import com.googlecode.lanterna.terminal.Terminal

class ConsoleKeyboardListener(val terminal: Terminal): InputListener {

    private fun keyStrokeToInputData(keyStroke: KeyStroke): InputData? {
        return when(keyStroke.keyType) {
            KeyType.ArrowRight -> InputData.RIGHT
            KeyType.ArrowUp -> InputData.UP
            KeyType.ArrowLeft -> InputData.LEFT
            KeyType.ArrowDown -> InputData.DOWN
            else -> null
        }
    }

    override fun readInput(): InputData {
        var inputData: InputData? = null
        do {
            inputData =  keyStrokeToInputData(terminal.readInput())
        } while (inputData == null)
        return inputData
    }
}