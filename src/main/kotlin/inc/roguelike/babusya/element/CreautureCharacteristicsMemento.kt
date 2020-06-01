package inc.roguelike.babusya.element

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName

class CreautureCharacteristicsMemento {
    companion object {
        private const val name = "CreatureCharacteristics"

        fun deserialize(line: String): CreatureCharacteristics? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.name || args.size != 3) {
                null
            } else {
                try {
                    CreatureCharacteristics(
                        args[0].toInt(),
                        args[1].toInt(),
                        args[2].toInt()
                    )
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }

        fun serialize(creatureCharacteristics: CreatureCharacteristics): String {
            return collectToString(name, listOf(creatureCharacteristics.hitPoints.toString(),
                creatureCharacteristics.maxHitPoints.toString(), creatureCharacteristics.attack.toString()))
        }
    }
}