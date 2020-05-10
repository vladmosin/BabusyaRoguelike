package inc.roguelike.babusya.inputListeners

import InputListener
import inc.roguelike.babusya.network.Client

class NetworkListener(client: Client): InputListener {
    override fun addCommand(command: (InputData) -> Unit): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}