package inc.roguelike.babusya.element.concrete.memento

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.concrete.ConfusableCreature
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName

class ConfusableCreatureMemento {
    companion object {
        private const val name = "ConfusableCreature"

        fun deserialize(controllerFactory: ControllerFactory, line: String): ConfusableCreature? {
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
                    ConfusableCreature(creature, controller)
                }
            }
        }

        fun serialize(confusableCreature: ConfusableCreature): String {
            return collectToString(name, listOf(confusableCreature.creature.serialize(),
                confusableCreature.randomController.serialize()))
        }
    }
}