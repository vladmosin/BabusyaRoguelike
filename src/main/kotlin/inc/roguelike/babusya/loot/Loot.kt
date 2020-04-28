package inc.roguelike.babusya.loot

interface Loot {
    fun use(inventory: Inventory)

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
