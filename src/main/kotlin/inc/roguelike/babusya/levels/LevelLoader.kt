package inc.roguelike.babusya.levels

import InputListener
import inc.roguelike.babusya.FileSystem
import inc.roguelike.babusya.map.rectangularMap.RectangularMap

class LevelLoader(val inputListener: InputListener) {

    fun loadLevel(id: Int): Level {
        val serializedLevel = FileSystem.loadFile("Levels/Level${id}")
        val map = RectangularMap.deserialize(serializedLevel, inputListener)
        return Level(map!!, "Loaded level $id", id)
    }
}