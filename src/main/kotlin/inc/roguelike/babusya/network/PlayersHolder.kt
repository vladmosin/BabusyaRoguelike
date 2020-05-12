package inc.roguelike.babusya.network

import inc.roguelike.babusya.element.concrete.Hero

/**
 * Stores active players for concrete game
 * */
class PlayersHolder {
    var players = ArrayList<Player>()
    val newClients = ArrayList<Int>()

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
        val updatedPlayers = ArrayList<Player>()
        for (player in players) {
            if (player.id != id) {
                updatedPlayers.add(player)
            }
        }

        players = updatedPlayers
    }

    /**
     * Returns recently connected ids
     * */
    fun newClients(): List<Int> {
        val clients = ArrayList(newClients)
        newClients.clear()
        return clients
    }
}
