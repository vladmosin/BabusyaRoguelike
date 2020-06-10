package inc.roguelike.babusya.element.concrete.memento

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.Monster
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName

/**
 * Memento for monstr
 * */
class MonsterMemento {
    companion object {
        private const val name = "Monster"

        /**
         * Deserializes monster
         * */
        fun deserialize(controllerFactory: ControllerFactory, line: String): Monster? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.name || args.size != 4) {
                null
            } else {
                val elementStatus =
                    ElementStatus.deserialize(args[3])
                val creatureCharacteristics =
                    CreatureCharacteristics.deserialize(args[0])
                val controller = controllerFactory.deserializeController(args[1])

                if (controller == null || elementStatus == null || creatureCharacteristics == null) {
                    null
                } else {
                    Monster(creatureCharacteristics, controller, args[2], elementStatus)
                }
            }
        }

        /**
         * Serializes monster
         * */
        fun serialize(monster: Monster): String {
            return collectToString(
                name, listOf(monster.characteristics.serialize(),
                monster.actionController!!.serialize(), monster.id, monster.elementStatus.name))
        }
    }
}