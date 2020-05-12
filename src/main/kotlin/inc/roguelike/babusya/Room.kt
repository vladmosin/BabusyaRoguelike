package inc.roguelike.babusya

import inc.roguelike.babusya.network.Client
import inc.roguelike.babusya.network.Player
import inc.roguelike.babusya.network.PlayersHolder

class Room(private val game: Game, private val id: Int, client: Client) {
    private val playersHolder = PlayersHolder()

    init {
        playersHolder.addClient(client)
    }

    fun addClient(client: Client) {
        playersHolder.addClient(client)
    }
}