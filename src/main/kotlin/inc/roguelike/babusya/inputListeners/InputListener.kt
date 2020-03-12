import inc.roguelike.babusya.inputListeners.InputData

interface InputListener {
    fun addCommand(command: (InputData) -> Unit): Int
    fun removeCommand(id: Int)
}