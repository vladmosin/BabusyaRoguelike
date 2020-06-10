package inc.roguelike.babusya.element

/**
 * Stores status of game element.
 * Creature dies when its hit points decrease to 0.
 * */
enum class ElementStatus {

    ALIVE, DEAD;

    companion object {
        /**
         * Deserializes element status
         * */
        fun deserialize(string: String): ElementStatus? {
            return try {
                valueOf(string)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
}
