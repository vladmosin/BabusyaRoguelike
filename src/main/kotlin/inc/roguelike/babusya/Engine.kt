package inc.roguelike.babusya

import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.controllers.HeroActionController
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.inputListeners.NetworkListener
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.network.PlayersHolder
import java.lang.Thread.sleep

/**
 * Launches game systems
 */
class Engine(val renderSystem: RenderSystem, val actionSystem: ActionSystem) {

    fun tick(gameState: GameState) {
        renderSystem.render(gameState.getLevel(), gameState.gameLog)
        val level = gameState.getLevel()
        //addNewPlayers(level.getMap())
        actionSystem.action()
    }

    private fun addNewPlayers(gameMap: GameMap) {
        val playersHolder = actionSystem.playersHolder
        val newClients = playersHolder.newClients()

        val heroes = newClients
            .map { client -> NetworkListener(client) }
            .map { inputListener -> HeroActionController(gameMap, inputListener) }
            .map { controller -> Hero.create(controller) }

        for (hero in heroes) {
            gameMap.addCreature(hero)
            actionSystem.addElement(hero)
        }
    }
}
