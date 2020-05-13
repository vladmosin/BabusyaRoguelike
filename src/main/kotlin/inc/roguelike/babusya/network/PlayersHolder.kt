package inc.roguelike.babusya.network

import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.inputListeners.InputData
import kotlinx.coroutines.runBlocking

/**
 * Stores active players for concrete game
 * */
class PlayersHolder {
    val newClients = ArrayList<Int>()
    var players = ArrayList<Player>()

    /**
     * Adds new client's id
     * */
    fun addClient(client: Int) {
        newClients.add(client)
    }

    /**
     * Checks that player did not disconnect
     * */
    fun isPlayerActive(hero: Hero): Boolean {
        for (player in players) {
            if (player.hero == hero) {
                return true
            }
        }

        return false
    }

    /**
     * Adds player to current list of players
     * */
    fun addPlayer(player: Player) {
        players.add(player)
    }

    /**
     * Removes players who disconnects
     * */
    fun removePlayerById(id: Int) {
        println("HERE WE ARE!")
        val removedPlayer: Player = players.find { it.id == id } ?: return

        removedPlayer.hero?.elementStatus = ElementStatus.DEAD
        players.remove(removedPlayer)

        runBlocking { removedPlayer.setInputData(InputData.INVENTORY_TOGGLE) }
    }

    /**
     * Returns recently connected ids
     * */
    fun newClients(): List<Int> {
        val clients = ArrayList<Int>(newClients)
        newClients.removeAll(clients)
        return clients
    }
}
