package inc.roguelike.babusya.loot

import inc.roguelike.babusya.loot.memento.LootMemento

/**
 * Item which can be collected and used by hero
 */
interface Loot {
    /**
     * Use given inventory
     * */
    fun use(inventory: Inventory)

    /**
     * Returns inventory description
     * */
    fun getDescrition(): String

    /**
     * Serializes loot
     * */
    fun serialize(): String

    companion object {
        /**
         * Deserializes loot
         * */
        fun deserialize(line: String): Loot? {
            return LootMemento.deserialize(line)
        }
    }
}
