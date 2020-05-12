package inc.roguelike.babusya.inputListeners

import InputListener
import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicInteger

abstract class AbstractInputListener: InputListener {
    var commandId = AtomicInteger(0)
    val commandQueue = ConcurrentLinkedQueue<(InputData) -> Unit>()

    /**
     * Adds command.
     * Received input processed by commands in order of registration
     * Returns unique command identification
     */
    override fun addCommand(command: (InputData) -> Unit): Int {
        val id = commandId.getAndIncrement()
        commandQueue.add(command)
        return id
    }


    var job: Job? = null

    protected abstract fun readInput(): InputData

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