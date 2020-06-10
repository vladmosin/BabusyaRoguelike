import inc.roguelike.babusya.commands.AbstractCommand

/**
 * Receives input and processes it using registered commands
 */
interface InputListener {
    /**
     * Adds command. It will be executed only once
     * Received input processed by commands in order of registration
     * Returns unique command identification
     */
    fun addCommand(command: (AbstractCommand) -> Unit): Int

}
