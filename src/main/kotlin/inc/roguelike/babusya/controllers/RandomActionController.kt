package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.map.GameMap
import java.util.*

/**
 * Does not pay attention to such unimportant things as enemies position
 * */
class RandomActionController(gameMap: GameMap): AbstractActionController(gameMap) {
    override fun makeTurn(creature: Creature): Boolean {
        val fromCell = gameMap.getCellByElement(creature)!!
        var ways = arrayListOf(gameMap.getRighterCell(fromCell), gameMap.getUpperCell(fromCell),
            gameMap.getLefterCell(fromCell), gameMap.getDownerCell(fromCell))
        ways = ways.filter { it != null && !it.storesActiveItem() }.toCollection(ArrayList())
        ways.add(fromCell)
        makeMove(creature, ways.random()!!)
        return true
    }

    override fun clone(): RandomActionController {
        return RandomActionController(gameMap)
    }

    override fun serialize(): String {
        return collectToString(ControllerType.RandomController.name, listOf())
    }

    override fun setDeserializeInfo(args: List<String>): ActionController? {
        return this
    }
}
