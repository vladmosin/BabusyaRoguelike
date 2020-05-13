package inc.roguelike.babusya.effects

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.concrete.Monster
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import java.lang.NumberFormatException

/**
 * Special case of PunchEffect, which does not punch monsters
 * */
class MonsterPunchEffect(damage: Int) : PunchEffect(damage) {
    override fun visitMonster(monster: Monster): Boolean = false

    override fun serialize() = collectToString(name, listOf(damage.toString()))

    companion object {
        private const val name = "PunchEffect"

        fun deserialize(line: String): MonsterPunchEffect? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.name || args.size != 1) {
                null
            } else {
                try {
                    MonsterPunchEffect(args[0].toInt())
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }
    }
}
