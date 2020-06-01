package inc.roguelike.babusya.commands

import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.map.GameMap

/**
 * Command for moving inventory pointer down
 * */
class InventoryDownCommand: AbstractCommand {

    /**
     * Moves inventory pointer down
     * */
    override fun apply(gameMap: GameMap, creature: Hero, log: GameLog?): Any? {
        creature.inventory.selectNextLoot()
        return null
    }
}
