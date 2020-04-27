package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.map.shortestPath

/**
 * Implementation of aggressive strategy for mobs
 * Mobs with this strategy try to get closer to their target and attack
 * */
class AggressiveController(gameMap: GameMap, var attackTarget: GameElement?): AbstractActionController(gameMap) {
    private var positionX: Int = -1
    private var positionY: Int = -1

    override fun makeTurn(creature: Creature) {
        if (attackTarget == null) {
            return
        }
        val fromCell = gameMap.getCellByElement(creature)
        val targetCell = gameMap.getCellByElement(attackTarget!!)

        val path = shortestPath(gameMap, fromCell!!, targetCell!!) ?: return
        makeMove(creature, path[1])
    }

    override fun clone(): AggressiveController {
        if (attackTarget != null) {
            val cell = gameMap.getCellByElement(attackTarget!!)
            if (cell != null) {
                val position = gameMap.positionOnScreen(cell)
                positionX = position.first
                positionY = position.second
            }
        }
        return AggressiveController(gameMap, null)
    }

    override fun serialize(): String {
        var x = -1
        var y = -1
        if (attackTarget != null) {
            val cell = gameMap.getCellByElement(attackTarget!!)
            if (cell != null) {
                val position = gameMap.positionOnScreen(cell)
                x = position.first
                y = position.second
            }
        }

        return collectToString(ControllerType.HeroController.name, listOf(x.toString(), y.toString()))
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
            attackTarget = gameMap.cellByPosition(positionX, positionY)?.storedItem
        }
    }
}
