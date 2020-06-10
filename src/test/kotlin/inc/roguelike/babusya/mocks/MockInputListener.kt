package inc.roguelike.babusya.mocks

import InputListener
import inc.roguelike.babusya.commands.AbstractCommand
import inc.roguelike.babusya.inputListeners.InputData
import java.util.*

/**
 * Any added command will be immediately executed on first input in inputQueue
 *  if it is not empty
 */
class MockInputListener(val inputQueue: Queue<AbstractCommand>): InputListener {

    private var cur_id = 0
    override fun addCommand(command: (AbstractCommand) -> Unit): Int {
        val lastInput = inputQueue.poll()
        if (lastInput != null) {
            command(lastInput)
        }
        return cur_id++;
    }

    fun addInput(abstractCommand: AbstractCommand): MockInputListener {
        inputQueue.add(abstractCommand)
        return this
    }

}
