package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.visitors.ElementVisitor


/**
 * Stores creature which can be confused.
 * Confusing means that creature strategy changes
 * and for given number of steps becomes defined by randomController
 * */
class ConfusableCreature(val creature: Creature, private var randomController: ActionController): Creature by creature {
    var moreStepsWhileConfused = 0

    override fun makeTurn() {
        if (moreStepsWhileConfused > 0) {
            moreStepsWhileConfused--
            randomController.makeTurn(this)
        } else {
            creature.actionController?.makeTurn(this)
        }
    }

    override fun serialize(): String {
        return collectToString(name, listOf(creature.serialize(), randomController.serialize()))
    }

    override fun <T> accept(visitor: ElementVisitor<T>): T {
        return visitor.visitConfused(this)
    }

    override fun clone(): ConfusableCreature {
        val confusableCreature = ConfusableCreature(creature.clone(), randomController.clone())
        confusableCreature.moreStepsWhileConfused = moreStepsWhileConfused

        return confusableCreature
    }

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
    }
}
