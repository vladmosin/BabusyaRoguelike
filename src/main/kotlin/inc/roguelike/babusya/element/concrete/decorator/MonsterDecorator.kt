package inc.roguelike.babusya.element.concrete.decorator

import inc.roguelike.babusya.element.concrete.Monster
import inc.roguelike.babusya.element.interfaces.Creature

interface MonsterDecorator {
    fun applyEffect(creature: Creature) {}

    fun makeTurn(creature: Creature) = false
}