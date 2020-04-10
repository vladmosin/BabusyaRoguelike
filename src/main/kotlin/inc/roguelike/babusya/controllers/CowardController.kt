package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.gameElement.Creature
import inc.roguelike.babusya.gameElement.GameElement
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.map.shortestPath


class CowardController(gameMap: GameMap, var scaryElement: GameElement?): AbstractActionController(gameMap) {
    override fun makeTurn(creature: Creature) {
        if (scaryElement == null) {
            return
        }
        val fromCell = gameMap.getCellByElement(creature)
        val targetCell = gameMap.getCellByElement(scaryElement!!)

        val path = shortestPath(gameMap, fromCell!!, targetCell!!) ?: return

        val toCell = path[1]
        val ways = listOf(gameMap.getRighterCell(fromCell), gameMap.getUpperCell(fromCell),
            gameMap.getLefterCell(fromCell), gameMap.getDownerCell(fromCell))

        for (i in ways.indices) {
            if (ways[i] == toCell) {
                makeMove(creature, ways[(i + 2) % 4] ?: ways[(i + 1) % 4] ?: ways[(i + 3) % 4] ?: fromCell)
            }
        }

    }
}
