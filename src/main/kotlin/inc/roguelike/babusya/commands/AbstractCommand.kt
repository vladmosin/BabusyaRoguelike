package inc.roguelike.babusya.commands

import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.map.GameMap

/**
 * Wrapper for commands
 * */
interface AbstractCommand {

    /**
     * Takes an input and do some operations specific for this command
     * */
    fun apply(gameMap: GameMap, creature: Hero, log: GameLog?): Any?
}
