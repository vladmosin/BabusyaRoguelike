package inc.roguelike.babusya.element.concrete.decorator

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.element.concrete.Monster
import inc.roguelike.babusya.element.interfaces.Creature

class ConfuseDecorator(val randomController: ActionController): MonsterDecorator {
    override fun makeTurn(creature: Creature): Boolean {
        randomController.makeTurn(creature)
        return true
    }
}