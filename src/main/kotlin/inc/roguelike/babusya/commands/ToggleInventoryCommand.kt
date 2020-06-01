package inc.roguelike.babusya.commands

import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.map.GameMap

/**
 * Command for inventory toggling
 * */
class ToggleInventoryCommand: AbstractCommand {

    /**
     * Toggles inventory
     * */
    override fun apply(gameMap: GameMap, creature: Hero, log: GameLog?): Any? {
        creature.inventory.useSelected()
        return null
    }
}
