package inc.roguelike.babusya

import inc.roguelike.babusya.network.Player
import inc.roguelike.babusya.network.PlayersHolder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Room(
    val game: Game,
    val id: Int,
    val playersHolder: PlayersHolder
) {
    private var isNew = true

    fun addClient(client: Int) {
        playersHolder.addClient(client)
        if (isNew) {
            isNew = false
            launch()
        }
    }

    private fun launch() {
        println("Launch room")
        GlobalScope.launch {
            game.launch()
        }
    }

    private fun stop() {
        game.endGame()
        isNew = true
    }

    fun removePlayer(player: Player) {
        playersHolder.removePlayerById(player.id)
        if (playersHolder.newClients.isEmpty() && playersHolder.players.isEmpty()) {
            stop()
        }
    }

    fun findPlayer(id: Int): Player? {
        for (player in playersHolder.players) {
            if (player.id == id) {
                return player
            }
        }

        return null
    }
}
