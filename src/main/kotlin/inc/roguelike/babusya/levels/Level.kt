package inc.roguelike.babusya.levels
import inc.roguelike.babusya.map.GameMap

/**
 * Holds abstraction of Level in game
 * */
class Level(private var map: GameMap, private var name: String, private val id: Int) {

    fun getMap() = map

    fun getName() = name

    fun getId() = id

    fun clone() = Level(map.clone(), name, id)
}
