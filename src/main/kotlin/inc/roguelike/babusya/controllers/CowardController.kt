package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.map.shortestPath

//Will stop and fight if player is on neighbour cell
class CowardController(gameMap: GameMap, var scaryElement: GameElement?): AbstractActionController(gameMap) {
    private var positionX = -1
    private var positionY = -1

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

    override fun clone(): CowardController {
        if (scaryElement != null) {
            val cell = gameMap.getCellByElement(scaryElement!!)
            if (cell != null) {
                val position = gameMap.positionOnScreen(cell)
                positionX = position.first
                positionY = position.second
            }
        }
        return CowardController(gameMap, null)
    }

    override fun serialize(): String {
        var x = -1
        var y = -1
        if (scaryElement != null) {
            val cell = gameMap.getCellByElement(scaryElement!!)
            if (cell != null) {
                val position = gameMap.positionOnScreen(cell)
                x = position.first
                y = position.second
            }
        }

        return collectToString(ControllerType.CowardController.name, listOf(x.toString(), y.toString()))
    }

    override fun setDeserializeInfo(args: List<String>): ActionController? {
        if (args.size != 2) {
            return null
        }

        return try {
            positionX = args[0].toInt()
            positionY = args[1].toInt()

            this
        } catch (e: NumberFormatException) {
            null
        }
    }

    override fun changeGameMap(gameMap: GameMap) {
        super.changeGameMap(gameMap)
        if (positionX != -1 && positionY != -1) {
            scaryElement = gameMap.cellByPosition(positionX, positionY)?.storedItem
        }
    }
}
