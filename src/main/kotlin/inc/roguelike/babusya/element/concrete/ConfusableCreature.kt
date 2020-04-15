package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.visitors.ElementVisitor

class ConfusableCreature(val creature: Creature, private val randomController: ActionController): Creature by creature {
    var moreStepsWhileConfused = 0

    override fun makeTurn() {
        if (moreStepsWhileConfused > 0) {
            moreStepsWhileConfused--;
            randomController.makeTurn(this)
        } else {
            //TODO does not support decorators chains
            creature.actionController?.makeTurn(this)
        }
    }

    override fun <T> accept(visitor: ElementVisitor<T>): T {
        return visitor.visitConfused(this)
    }
}
