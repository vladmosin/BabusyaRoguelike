package inc.roguelike.babusya.engines

import inc.roguelike.babusya.GameState
import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.actionSystems.ActionSystem
import inc.roguelike.babusya.actionSystems.MultiplayerActionSystem
import inc.roguelike.babusya.controllers.HeroActionController
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.inputListeners.EmptyInputListener
import inc.roguelike.babusya.inputListeners.NetworkListener
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.network.Player
import inc.roguelike.babusya.network.PlayersHolder
import java.lang.Thread.sleep

/**
 * Launches game systems
 */
class MultiPlayerEngine(val actionSystem: MultiplayerActionSystem): Engine {

    override fun tick(gameState: GameState) {
        val level = gameState.getLevel()
        addNewPlayers(level.getMap())
        actionSystem.action()
    }

    override fun actionSystem() = actionSystem

    var msg = "ABC"

    private fun addNewPlayers(gameMap: GameMap) {
        if (actionSystem.playersHolder.newClients.size > 0) {
            println("YEP ${actionSystem.playersHolder.newClients.size}")
        } else {
            if (msg.isNotEmpty()) {
                println("addNewPlayers ${actionSystem.playersHolder.newClients}")
                msg = ""
            }
        }

        val playersHolder = actionSystem.playersHolder
        val newClients = playersHolder.newClients()

        val players = newClients.map { id -> Player(null, id) }
        val listeners = players.map { player -> NetworkListener(player) }

        val heroes = listeners
            .map { listener -> HeroActionController(gameMap, listener) }
            .map { controller -> Hero.create(controller) }

        for (listener in listeners) {
            listener.start()
        }

        for (i in heroes.indices) {
            players[i].hero = heroes[i]
            playersHolder.addPlayer(players[i])
        }

        for (hero in heroes) {
            gameMap.addCreature(hero)
            actionSystem.addElement(hero)
        }
    }
}
