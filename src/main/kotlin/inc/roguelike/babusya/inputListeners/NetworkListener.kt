package inc.roguelike.babusya.inputListeners

import inc.roguelike.babusya.network.Player

class NetworkListener(private val player: Player): AbstractInputListener() {
    override suspend fun readInput(): InputData {
        return player.receiveInputData()
    }
}
