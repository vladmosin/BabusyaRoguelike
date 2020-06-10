package inc.roguelike.babusya.commands

import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.GameMap

/**
 * Wrapper for moving commands that moves creature to some direction (left, right, up, down)
 * */
interface MovingCommand: AbstractCommand {

    /**
     * Takes an input and moves creature to some direction from given cell
     * */
    fun apply(gameMap: GameMap, creature: Creature, log: GameLog?, cell: Cell): Cell
}
