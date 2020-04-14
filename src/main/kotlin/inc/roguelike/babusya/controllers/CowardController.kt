package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.map.shortestPath

//Will stop and fight if player is on neighbour cell
class CowardController(gameMap: GameMap, var scaryElement: GameElement?): AbstractActionController(gameMap) {
    override fun makeTurn(creature: Creature) {
        if (scaryElement == null) {
            return
        }
        val fromCell = gameMap.getCellByElement(creature)
        val targetCell = gameMap.getCellByElement(scaryElement!!)

        val path = shortestPath(gameMap, fromCell!!, targetCell!!) ?: return

        val toCell = path[1]
        var ways = listOf(gameMap.getRighterCell(fromCell), gameMap.getUpperCell(fromCell),
            gameMap.getLefterCell(fromCell), gameMap.getDownerCell(fromCell))
        ways = ways.map { cell -> if (cell != null && !cell.storesActiveItem()) cell else null  }
        for (i in ways.indices) {
            if (ways[i] == toCell) {
                makeMove(creature, ways[(i + 2) % 4] ?: ways[(i + 1) % 4] ?: ways[(i + 3) % 4] ?: fromCell)
            }
        }
    }
}
