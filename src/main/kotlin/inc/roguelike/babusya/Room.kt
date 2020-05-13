package inc.roguelike.babusya

import inc.roguelike.babusya.network.Player
import inc.roguelike.babusya.network.PlayersHolder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Stores and manages one game on server
 * */
class Room(
    val game: Game,
    val id: Int,
    val playersHolder: PlayersHolder
) {
    private var isNew = true

    /**
     * Adds new player with given id
     * */
    fun addClient(client: Int) {
        playersHolder.addClient(client)
        if (isNew) {
            isNew = false
            launch()
        }
    }

    private fun launch() {
        println("Launch room")
        game.resume()
        GlobalScope.launch {
            game.launch()
        }
    }

    private fun stop() {
        game.endGame()
        isNew = true
    }

    /**
     * Removes player from game
     * */
    fun removePlayer(player: Player) {
        playersHolder.removePlayerById(player.id)
        if (playersHolder.newClients.isEmpty() && playersHolder.players.isEmpty()) {
            stop()
        }
    }

    /**
     * Finds player in a game, or returns null if he does not exists
     * */
    fun findPlayer(id: Int): Player? = playersHolder.players.find { it.id == id }
}
