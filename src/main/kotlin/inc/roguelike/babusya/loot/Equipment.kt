package inc.roguelike.babusya.loot

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import kotlin.math.min

open class Equipment(val type: EquipmentType, val hpBonus: Int, val attackBonus: Int): Loot {
    override fun use(inventory: Inventory) {
        inventory.equip(this)
    }

    override fun serialize(): String {
        return collectToString(name, listOf(type.name, hpBonus.toString(), attackBonus.toString()))
    }

    fun addBuffs(hero: Hero) {
        hero.characteristics.maxHitPoints += hpBonus
        hero.characteristics.attack += attackBonus
    }

    fun removeBuffs(hero: Hero) {
        hero.characteristics.maxHitPoints -= hpBonus
        hero.characteristics.hitPoints = min(hero.characteristics.hitPoints, hero.characteristics.maxHitPoints)
        hero.characteristics.attack -= attackBonus
    }

    companion object {
        private const val name = "Equipment"

        fun deserialize(line: String): Equipment? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.name || args.size != 3) {
                null
            } else {
                val type = EquipmentType.deserialize(args[0]) ?: return null
                try {
                    return Equipment(type, args[0].toInt(), args[1].toInt())
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }
    }

}
