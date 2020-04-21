package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.map.shortestPath

/**
 * Implementation of aggressive strategy for mobs
 * Mobs with this strategy try to get closer to their target and attack
 * */
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

    override fun clone(gameElement: GameElement): AggressiveController {
        return AggressiveController(gameMap, gameElement)
    }
}
