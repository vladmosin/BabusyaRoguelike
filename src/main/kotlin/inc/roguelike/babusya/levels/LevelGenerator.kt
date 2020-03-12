package inc.roguelike.babusya.levels

import InputListener
import inc.roguelike.babusya.map.RectangularMap

class LevelGenerator(val inputListener: InputListener) {
    fun generateLevel(id: Int): Level {
        val map = RectangularMap(3, 3)
        map.generateMap(inputListener)
        return Level(map, "Level: " + id)
    }
}