package inc.roguelike.babusya.network

import inc.roguelike.babusya.element.concrete.Hero

/**
 * Stores active players for concrete game
 * */
class PlayersHolder {
    private val players = ArrayList<Player>()
    private val newClients = ArrayList<Client>()

    fun addClient(client: Client) {
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
    fun removeDisconnectedPlayers() {
        TODO()
    }

    /**
     * Returns recently connected clients
     * */
    fun newClients(): List<Client> {
        val clients = ArrayList(newClients)
        newClients.clear()
        return clients
    }

    /**
     * Sends updated state to all players
     * */
    fun sendMessage(message: Message) {
        for (player in players) {
            player.sendMessage(message)
        }
    }
}
