import inc.roguelike.babusya.inputListeners.InputData

/**
 * Receives input and processes it using registered commands
 */
interface InputListener {
    /**
     * Adds command.
     * Received input processed by commands in order of registration
     * Returns unique command identification
     */
    fun addCommand(command: (InputData) -> Unit): Int

    /**
     * Removes command by id
     */
    fun removeCommand(id: Int)
}