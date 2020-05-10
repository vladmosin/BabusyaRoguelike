package inc.roguelike.babusya.network

import inc.roguelike.babusya.element.concrete.Hero

/**
 * Stores active players for concrete game
 * */
class PlayersHolder {
    private val players = ArrayList<Player>()

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
        TODO()
    }
}