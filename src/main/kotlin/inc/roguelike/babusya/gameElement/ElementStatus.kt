package inc.roguelike.babusya.gameElement

/**
 * Stores status of game element.
 * Creature dies when its hit points decrease to 0.
 * */
enum class ElementStatus {

    ALIVE, DEAD;

    companion object {
        fun deserialize(string: String): ElementStatus? {
            return try {
                valueOf(string)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
}
