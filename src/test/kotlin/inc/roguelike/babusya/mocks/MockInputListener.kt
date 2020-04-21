package inc.roguelike.babusya.mocks

import InputListener
import inc.roguelike.babusya.inputListeners.InputData
import java.util.*

/**
 * Any added command will be immediately executed on first input in inputQueue
 *  if it is not empty
 */
class MockInputListener(val inputQueue: Queue<InputData>): InputListener {

    private var cur_id = 0
    override fun addCommand(command: (InputData) -> Unit): Int {
        val lastInput = inputQueue.poll()
        if (lastInput != null) {
            command(lastInput)
        }
        return cur_id++;
    }

    fun addInput(inputData: InputData): MockInputListener {
        inputQueue.add(inputData)
        return this
    }

}
