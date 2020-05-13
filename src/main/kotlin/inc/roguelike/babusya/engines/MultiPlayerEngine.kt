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

    /**
     * Processes one tick of multiplayer game
     * */
    override fun tick(gameState: GameState) {
        println("ENGINE TICK")

        addNewPlayers(gameState)
        actionSystem.action()
    }

    /**
     * Returns action system
     * */
    override fun actionSystem() = actionSystem

    private fun addNewPlayers(gameState: GameState) {
        val gameMap = gameState.getLevel().getMap()
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
            heroes[i].actionController?.useLog(gameState.gameLog)
        }

        for (hero in heroes) {
            println("Add hero ${hero.id}")
            gameMap.addCreature(hero)
            actionSystem.addElement(hero)
        }
    }
}
