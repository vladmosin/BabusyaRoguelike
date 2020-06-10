package inc.roguelike.babusya.loot.memento

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.loot.Equipment
import inc.roguelike.babusya.loot.EquipmentType

/**
 * Memento implementation for equipment
 * */
class EquipmentMemento {
    companion object {
        private const val name = "Equipment"

        /**
         * Deserializes memento
         * */
        fun deserialize(line: String): Equipment? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.name || args.size != 3) {
                null
            } else {
                val type = EquipmentType.deserialize(args[0]) ?: return null
                try {
                    return Equipment(type, args[1].toInt(), args[2].toInt())
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }

        /**
         * Serializes memento
         * */
        fun serialize(equipment: Equipment): String {
            return collectToString(name, listOf(equipment.type.name,
                equipment.hpBonus.toString(), equipment.attackBonus.toString()))
        }
    }
}

