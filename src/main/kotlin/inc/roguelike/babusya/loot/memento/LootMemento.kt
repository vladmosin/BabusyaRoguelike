package inc.roguelike.babusya.loot.memento

import inc.roguelike.babusya.loot.Equipment
import inc.roguelike.babusya.loot.Loot
import inc.roguelike.babusya.loot.Potion

/**
 * Memento implementation for loot
 * */
class LootMemento {
    companion object {
        /**
         * Deserializes loot
         * */
        fun deserialize(line: String): Loot? {
            val deserializers = listOf(
                { s: String -> Equipment.deserialize(s) },
                { s: String -> Potion.deserialize(s) }
            )

            for (deserializer in deserializers) {
                val gameElement = deserializer(line)
                if (gameElement != null) {
                    return gameElement
                }
            }

            return null
        }
    }
}