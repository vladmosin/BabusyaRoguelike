package inc.roguelike.babusya.commands

import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.map.GameMap

/**
 * Command for moving inventory pointer up
 * */
class InventoryUpCommand: AbstractCommand {

    /**
     * Moves inventory pointer up
     * */
    override fun apply(gameMap: GameMap, creature: Hero, log: GameLog?): Any? {
        creature.inventory.selectPreviousLoot()
        return null
    }
}
