package inc.roguelike.babusya.commands

import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.GameMap

/**
 * Command for moving to the left
 * */
class LeftCommand: MovingCommand {

    /**
     * Moves to the left
     * */
    override fun apply(gameMap: GameMap, creature: Creature, log: GameLog?, cell: Cell): Cell {
        return gameMap.getLefterCell(cell) ?: cell
    }

    override fun apply(gameMap: GameMap, creature: Hero, log: GameLog?): Any? {
        return null
    }
}
