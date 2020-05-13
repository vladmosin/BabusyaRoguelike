package inc.roguelike.babusya.inputListeners

import InputListener

class EmptyInputListener: InputListener {
    override fun addCommand(command: (InputData) -> Unit) = 0
}