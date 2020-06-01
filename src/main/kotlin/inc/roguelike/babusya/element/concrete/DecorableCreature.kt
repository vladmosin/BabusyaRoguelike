package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.concrete.decorator.ConfuseDecorator
import inc.roguelike.babusya.element.concrete.memento.ConfusableCreatureMemento
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.visitors.ElementVisitor


/**
 * Stores creature which can be confused.
 * Confusing means that creature strategy changes
 * and for given number of steps becomes defined by randomController
 * */
class DecorableCreature(val creature: Creature, var randomController: ActionController): Creature by creature {
    var moreStepsWhileConfused = 0
    val decorator = ConfuseDecorator(randomController)

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

    override fun clone(): DecorableCreature {
        val confusableCreature = DecorableCreature(creature.clone(), randomController.clone())
        confusableCreature.moreStepsWhileConfused = moreStepsWhileConfused

        return confusableCreature
    }

    companion object {
        fun deserialize(controllerFactory: ControllerFactory, line: String): DecorableCreature? {
            return ConfusableCreatureMemento.deserialize(controllerFactory, line)
        }
    }
}
