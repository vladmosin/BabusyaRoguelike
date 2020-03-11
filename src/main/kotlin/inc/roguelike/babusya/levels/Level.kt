package inc.roguelike.babusya.levels
import inc.roguelike.babusya.map.GameMap

class Level(private var map: GameMap, private var name: String) {

    fun getMap() = map

    fun getName() = name
}