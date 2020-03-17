package inc.roguelike.babusya.levels

import InputListener
import inc.roguelike.babusya.map.rectangularMap.RectangularMapBuilder

class LevelGenerator(val inputListener: InputListener) {

    fun generateLevel(id: Int): Level {
        val map = RectangularMapBuilder(10, 20).addHero(inputListener).buildMap()
        return Level(map, "Level: $id", id)
    }
}
