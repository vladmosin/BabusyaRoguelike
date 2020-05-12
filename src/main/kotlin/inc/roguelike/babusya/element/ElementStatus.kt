package inc.roguelike.babusya.element

/**
 * Stores status of game element.
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
