package inc.roguelike.babusya.inputListeners

import inc.roguelike.babusya.network.Client

class NetworkListener(private val client: Client): AbstractInputListener() {
    override fun readInput(): InputData {
        return client.receiveInputData()
    }
}