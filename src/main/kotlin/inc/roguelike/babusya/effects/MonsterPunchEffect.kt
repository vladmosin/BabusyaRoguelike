package inc.roguelike.babusya.effects

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.effects.memento.MonsterPunchEffectMemento
import inc.roguelike.babusya.element.concrete.Monster
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import java.lang.NumberFormatException

/**
 * Special case of PunchEffect, which does not punch monsters
 * */
class MonsterPunchEffect(damage: Int) : PunchEffect(damage) {
    // does not punch monsters
    override fun visitMonster(monster: Monster): Boolean = false

    override fun serialize() = MonsterPunchEffectMemento.serialize(this)

    companion object {
        private const val name = "PunchEffect"

        fun deserialize(line: String): MonsterPunchEffect? {
            return MonsterPunchEffectMemento.deserialize(line)
        }
    }
}
