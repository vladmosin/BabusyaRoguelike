package inc.roguelike.babusya.effects

import inc.roguelike.babusya.element.concrete.Monster

class MonsterPunchEffect(damage: Int) : PunchEffect(damage) {
    // does not punch monsters
    override fun visitMonster(monster: Monster): Boolean = false
}
