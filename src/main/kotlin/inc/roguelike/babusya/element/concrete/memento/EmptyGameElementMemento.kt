package inc.roguelike.babusya.element.concrete.memento

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.concrete.EmptyGameElement
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName

/**
 * Memento for empty game element
 * */
class EmptyGameElementMemento {
    companion object {
        const val name = "Empty"

        /**
         * Deserializes empty game element
         * */
        fun deserialize(line: String): EmptyGameElement? {
            val name = getName(line)
            val args = getArguments(line)

            return if (args == null || name == null || args.isNotEmpty() || name != this.name) {
                null
            } else {
                EmptyGameElement()
            }
        }

        /**
         * Serializes empty game element
         * */
        fun serialize(emptyGameElement: EmptyGameElement): String {
            return collectToString(name, listOf())
        }
    }
}