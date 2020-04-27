package inc.roguelike.babusya.controllers

/** Stores types of all controllers in a game */
enum class ControllerType {
    HeroController,
    AggressiveController,
    PassiveController,
    CowardController,
    RandomController;

    companion object {
        fun serialize(type: ControllerType): String {
            return type.name
        }

        fun deserialize(line: String): ControllerType? {
            return if (values().map { e -> e.name}.contains(line)) {
                valueOf(line)
            } else {
                null
            }
        }
    }
}
