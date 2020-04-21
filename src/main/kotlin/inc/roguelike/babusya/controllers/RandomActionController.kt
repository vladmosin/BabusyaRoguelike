package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.GameMap
import java.util.*

/**
 * Does not pay attention to such unimportant things as enemies position
 * */
class RandomActionController(gameMap: GameMap): AbstractActionController(gameMap) {
    override fun makeTurn(creature: Creature) {
        val fromCell = gameMap.getCellByElement(creature)!!
        var ways = arrayListOf(gameMap.getRighterCell(fromCell), gameMap.getUpperCell(fromCell),
            gameMap.getLefterCell(fromCell), gameMap.getDownerCell(fromCell))
        ways = ways.filter { it != null && !it.storesActiveItem() }.toCollection(ArrayList())
        ways.add(fromCell)
        makeMove(creature, ways.random()!!)
    }

    override fun clone(gameElement: GameElement): RandomActionController {
        return RandomActionController(gameMap)
    }
}
