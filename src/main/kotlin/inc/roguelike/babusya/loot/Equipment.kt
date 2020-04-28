package inc.roguelike.babusya.loot

import inc.roguelike.babusya.element.concrete.Hero
import kotlin.math.min

open class Equipment(val type: EquipmentType, val hpBonus: Int, val attackBonus: Int): Loot {
    override fun use(inventory: Inventory) {
        inventory.equip(this)
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

}
