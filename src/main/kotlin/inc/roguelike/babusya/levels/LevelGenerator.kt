package inc.roguelike.babusya.levels

import inc.roguelike.babusya.map.RectangularMap

class LevelGenerator {
    fun generateLevel(id: Int): Level {
        val map = RectangularMap(3, 3)
        return Level(map, "Level: " + id)
    }
}