package inc.roguelike.babusya.inputListeners

import inc.roguelike.babusya.network.Player
import inc.roguelike.babusya.network.PlayersHolder

class NetworkListener(private val player: Player, private val playersHolder: PlayersHolder): AbstractInputListener() {
    override suspend fun readInput(): InputData {
        val inputData = player.receiveInputData()
        if (inputData == InputData.QUIT) {
            playersHolder.removePlayerById(player.id)
            return InputData.INVENTORY_TOGGLE
        }
        return inputData
    }
}
