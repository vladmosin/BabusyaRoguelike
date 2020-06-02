package inc.roguelike.babusya.levels

import InputListener
import inc.roguelike.babusya.FileSystem
import inc.roguelike.babusya.map.rectangularMap.RectangularMap

/**
 * Loads level from file
 * */
class LevelLoader(val inputListener: InputListener) {

    /**
     * Loads level
     * */
    fun loadLevel(path: String, name: String): Level {
        val serializedLevel = FileSystem.loadFile(path)
        val map = RectangularMap.deserialize(serializedLevel, inputListener)
        return Level(map!!, name, 1)
    }
}
