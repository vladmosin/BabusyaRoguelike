package inc.roguelike.babusya.engines

import inc.roguelike.babusya.GameState
import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.actionSystems.ActionSystem
import inc.roguelike.babusya.actionSystems.MultiplayerActionSystem
import inc.roguelike.babusya.controllers.HeroActionController
import inc.roguelike.babusya.element.concrete.Hero
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

    private fun addNewPlayers(gameMap: GameMap) {
        val playersHolder = actionSystem.playersHolder
        val newClients = playersHolder.newClients()

        val heroes = newClients
            .map { client -> NetworkListener(client) }
            .map { inputListener -> HeroActionController(gameMap, inputListener) }
            .map { controller -> Hero.create(controller) }

        for (i in heroes.indices) {
            playersHolder.addPlayer(Player(heroes[i], newClients[i]))
        }

        for (hero in heroes) {
            gameMap.addCreature(hero)
            actionSystem.addElement(hero)
        }
    }
}
