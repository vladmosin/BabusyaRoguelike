package inc.roguelike.babusya.loot

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.loot.memento.EquipmentMemento
import kotlin.math.min

/**
 * Loot which can be equipped by hero
 * Modifies hero characteristics when equipped
 */
open class Equipment(val type: EquipmentType, val hpBonus: Int, val attackBonus: Int): Loot {
    /**
     * If hero is currently equipped with this item
     *  then it is taken off
     * otherwise it is putted on
     */
    override fun use(inventory: Inventory) {
        if (inventory.equipped[type] == this) {
            inventory.equip(EmptyEquipment(type))
        } else {
            inventory.equip(this)
        }
    }

    /**
     * Serializes equipment
     * */
    override fun serialize() = EquipmentMemento.serialize(this)

    /**
     * Add buffs for wearing equipment
     */
    fun addBuffs(hero: Hero) {
        hero.characteristics.maxHitPoints += hpBonus
        hero.characteristics.attack += attackBonus
    }

    /**
     * Remove buffs for wearing equipment
     */
    fun removeBuffs(hero: Hero) {
        hero.characteristics.maxHitPoints -= hpBonus
        hero.characteristics.hitPoints = min(hero.characteristics.hitPoints, hero.characteristics.maxHitPoints)
        hero.characteristics.attack -= attackBonus
    }

    /**
     * Returns description
     * */
    override fun getDescrition(): String {
        return "(Eq| ${type.name[0]}, hp: $hpBonus, attack: $attackBonus)"
    }
  
    companion object {
        /**
         * Deserializes equipment
         * */
        fun deserialize(line: String): Equipment? {
            return EquipmentMemento.deserialize(line)
        }
    }
}
