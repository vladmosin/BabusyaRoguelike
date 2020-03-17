package inc.roguelike.babusya.levels

import InputListener
import inc.roguelike.babusya.map.rectangularMap.RectangularMapBuilder

/**
 * Creates random level
 * */
class LevelGenerator(val inputListener: InputListener) {

    fun generateLevel(id: Int): Level {
        val map = RectangularMapBuilder(10, 20).addHero().addWalls().buildMap(inputListener)
        return Level(map, "Level: $id", id)
    }
}
