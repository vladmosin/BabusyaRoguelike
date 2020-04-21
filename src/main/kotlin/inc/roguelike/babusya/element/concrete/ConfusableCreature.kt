package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.visitors.ElementVisitor

class ConfusableCreature(val creature: Creature, private var randomController: ActionController): Creature by creature {
    var moreStepsWhileConfused = 0

    override fun makeTurn() {
        if (moreStepsWhileConfused > 0) {
            moreStepsWhileConfused--
            randomController.makeTurn(this)
        } else {
            //TODO does not support decorators chains
            creature.actionController?.makeTurn(this)
        }
    }

    override fun serialize(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> accept(visitor: ElementVisitor<T>): T {
        return visitor.visitConfused(this)
    }

    override fun clone(): ConfusableCreature {
        val confusableCreature = ConfusableCreature(creature.clone(), randomController)
        confusableCreature.randomController = randomController.clone(confusableCreature)
        confusableCreature.moreStepsWhileConfused = moreStepsWhileConfused

        return confusableCreature
    }

    companion object {
        private val name = "confused"

        fun deserialize(line: String): ConfusableCreature? {

        }
    }
}
