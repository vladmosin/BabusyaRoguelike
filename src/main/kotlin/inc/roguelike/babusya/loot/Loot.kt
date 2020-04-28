package inc.roguelike.babusya.loot

/**
 * Item which can be collected and used by hero
 */
interface Loot {
    fun use(inventory: Inventory)

    fun getDescrition(): String

    fun serialize(): String

    companion object {
        fun deserialize(line: String): Loot? {
            val deserializers = listOf(
                { s: String -> Equipment.deserialize(s) }
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
