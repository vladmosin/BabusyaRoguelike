package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.memento.ConfusableCreatureMemento
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.visitors.ElementVisitor


/**
 * Stores creature which can be confused.
 * Confusing means that creature strategy changes
 * and for given number of steps becomes defined by randomController
 * */
class ConfusableCreature(val creature: Creature, var randomController: ActionController): Creature by creature {
    var moreStepsWhileConfused = 0

    override fun makeTurn(): Boolean {
        if (moreStepsWhileConfused > 0) {
            moreStepsWhileConfused--
            return randomController.makeTurn(this)
        } else {
            return creature.actionController?.makeTurn(this) ?: true
        }
    }

    override fun serialize(): String {
        return ConfusableCreatureMemento.serialize(this)
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
        fun deserialize(controllerFactory: ControllerFactory, line: String): ConfusableCreature? {
            return ConfusableCreatureMemento.deserialize(controllerFactory, line)
        }
    }
}
