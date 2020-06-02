package inc.roguelike.babusya.element.concrete.decorator

import inc.roguelike.babusya.element.concrete.Monster
import inc.roguelike.babusya.element.interfaces.Creature

/**
 * Decorator for monster
 * */
interface MonsterDecorator {

    /**
     * Applies decorator effect
     * */
    fun applyEffect(creature: Creature) {}

    /**
     * Makes turn
     * */
    fun makeTurn(creature: Creature) = false
}