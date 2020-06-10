package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.GameMap

/**
 * Does not move
 * */
class PassiveController(gameMap: GameMap): AbstractActionController(gameMap) {

    /**
     * Always returns true and does not do nothing
     * */
    override fun makeTurn(creature: Creature): Boolean {
        return true
    }

    /**
     * Clones controller
     * */
    override fun clone(): PassiveController {
        return PassiveController(gameMap)
    }

    /**
     * Serializes controller
     * */
    override fun serialize(): String {
        return collectToString(ControllerType.PassiveController.name, listOf())
    }

    /**
     * Does not do anything
     * */
    override fun setDeserializeInfo(args: List<String>): ActionController? {
        return this
    }
}
