package inc.roguelike.babusya.levels
import inc.roguelike.babusya.map.GameMap

/**
 * Holds abstraction of Level in game
 * */
class Level(private var map: GameMap, private var name: String, private val id: Int) {

    /**
     * Returns map
     * */
    fun getMap() = map

    /**
     * Returns name
     * */
    fun getName() = name

    /**
     * Returns id
     * */
    fun getId() = id

    /**
     * Returns clone
     * */
    fun clone() = Level(map.clone(), name, id)
}
