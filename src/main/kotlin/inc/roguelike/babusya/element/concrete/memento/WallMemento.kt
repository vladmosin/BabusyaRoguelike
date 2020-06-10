package inc.roguelike.babusya.element.concrete.memento

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.Wall
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName

/**
 * Memento for wall
 * */
class WallMemento {
    companion object {
        private const val name = "Wall"

        /**
         * Deserializes wall
         * */
        fun deserialize(string: String): Wall? {
            val name = getName(string)
            val args = getArguments(string)

            if (name == null || args == null || args.size != 2 || name != this.name) {
                return null
            }

            val status = ElementStatus.deserialize(args[1])
            return if (status == null) {
                null
            } else {
                Wall(args[0], status)
            }
        }

        /**
         * Serializes wall
         * */
        fun serialize(wall: Wall): String {
            return collectToString(name, listOf(wall.id, wall.elementStatus.name))
        }
    }
}