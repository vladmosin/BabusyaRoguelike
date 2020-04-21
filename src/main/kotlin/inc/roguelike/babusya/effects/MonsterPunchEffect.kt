package inc.roguelike.babusya.effects

import inc.roguelike.babusya.element.concrete.Monster

/**
 * Special case of PunchEffect, which does not punch monsters
 * */
class MonsterPunchEffect(damage: Int) : PunchEffect(damage) {
    // does not punch monsters
    override fun visitMonster(monster: Monster): Boolean = false
}
