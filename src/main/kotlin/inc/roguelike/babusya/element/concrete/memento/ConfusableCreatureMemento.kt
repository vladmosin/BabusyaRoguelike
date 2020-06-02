package inc.roguelike.babusya.element.concrete.memento

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.concrete.DecorableCreature
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName

/**
 * Memento for confusable creature
 * */
class ConfusableCreatureMemento {
    companion object {
        private const val name = "ConfusableCreature"

        /**
         * Deserializes decorable creature
         * */
        fun deserialize(controllerFactory: ControllerFactory, line: String): DecorableCreature? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.name || args.size != 2) {
                null
            } else {
                val creature = Creature.deserialize(controllerFactory, args[0])
                val controller = controllerFactory.deserializeController(args[1])

                if (creature == null || controller == null) {
                    null
                } else {
                    DecorableCreature(creature, controller)
                }
            }
        }

        /**
         * Serializes decorable creature
         * */
        fun serialize(decorableCreature: DecorableCreature): String {
            return collectToString(name, listOf(decorableCreature.creature.serialize(),
                decorableCreature.randomController.serialize()))
        }
    }
}