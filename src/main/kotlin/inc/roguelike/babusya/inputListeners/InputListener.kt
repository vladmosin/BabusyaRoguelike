import inc.roguelike.babusya.inputListeners.InputData

interface InputListener {
    fun registerCommand(command: (InputData) -> Unit)
}