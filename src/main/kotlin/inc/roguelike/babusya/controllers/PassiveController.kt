package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.GameMap

/**
 * Implementation of passive strategy.
 * Mobs with this strategy do not move
 * */
class PassiveController(gameMap: GameMap): AbstractActionController(gameMap) {
    override fun makeTurn(creature: Creature): Boolean {
        return true
    }

    override fun clone(): PassiveController {
        return PassiveController(gameMap)
    }

    override fun serialize(): String {
        return collectToString(ControllerType.PassiveController.name, listOf())
    }

    override fun setDeserializeInfo(args: List<String>): ActionController? {
        return this
    }
}
