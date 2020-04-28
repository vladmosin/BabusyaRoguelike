package inc.roguelike.babusya.loot

enum class EquipmentType {
    WEAPON,
    ARMOR,
    HAT;

    companion object {
        fun deserialize(line: String): EquipmentType? {
            return if (values().map { e -> e.name}.contains(line)) {
                valueOf(line)
            } else {
                null
            }
        }
    }
}
