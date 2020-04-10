package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.gameElement.Creature
import inc.roguelike.babusya.gameElement.GameElement
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.map.shortestPath

class AggressiveController(gameMap: GameMap, var attackTarget: GameElement?): AbstractActionController(gameMap) {

    override fun makeTurn(creature: Creature) {
        if (attackTarget == null) {
            return
        }
        val fromCell = gameMap.getCellByElement(creature)
        val targetCell = gameMap.getCellByElement(attackTarget!!)

        val path = shortestPath(gameMap, fromCell!!, targetCell!!) ?: return
        makeMove(creature, path[1])
    }

}
