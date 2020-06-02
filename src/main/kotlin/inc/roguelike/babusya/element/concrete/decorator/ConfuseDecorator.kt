package inc.roguelike.babusya.element.concrete.decorator

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.element.concrete.Monster
import inc.roguelike.babusya.element.interfaces.Creature

/**
 * Confusion decorator
 * */
class ConfuseDecorator(val randomController: ActionController): MonsterDecorator {

    /**
     * Makes turn
     * */
    override fun makeTurn(creature: Creature): Boolean {
        return randomController.makeTurn(creature)
    }
}