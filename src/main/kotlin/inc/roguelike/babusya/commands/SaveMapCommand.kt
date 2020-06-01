package inc.roguelike.babusya.commands

import inc.roguelike.babusya.FileSystem
import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.map.GameMap
import java.io.File

/**
 * Command for saving game map
 * */
class SaveMapCommand: AbstractCommand {

    /**
     * Saves game map
     * */
    override fun apply(gameMap: GameMap, creature: Hero, log: GameLog?): Any? {
        FileSystem.saveToFile("Levels" + File.separator + "Saved", gameMap.serialize())
        log?.add("Game saved : successfully")
        return null
    }
}
