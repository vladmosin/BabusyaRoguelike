package inc.roguelike.babusya.levels
import InputListener
import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.map.rectangularMap.RectangularMap

/**
 * Holds abstraction of Level in game
 * */
class Level(private var map: GameMap, private var name: String, private val id: Int) {

    fun getMap() = map

    fun getName() = name

    fun getId() = id

    fun clone() = Level(map.clone(), name, id)

    fun serialize() = collectToString(name, listOf(map.serialize(), name, id.toString()))

    companion object {
        private val name = "Level"

        fun deserialize(line: String, inputListener: InputListener): Level? {
            val name = getName(line)
            val args = getArguments(line)

            if (name == null || args == null || name != this.name || args.size != 3) {
                return null
            }

            val map = RectangularMap.deserialize(args[0], inputListener) ?: return null
            return try {
                Level(map, args[1], args[2].toInt())
            } catch (e: NumberFormatException) {
                null
            }
        }
    }
}
