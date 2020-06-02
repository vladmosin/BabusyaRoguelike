package inc.roguelike.babusya.loot

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.loot.memento.InventoryMemento

/**
 * Loot collection
 * Item from inventory can be selected
 * Selected item can be used
 *
 * Hero can equip "Equipment" items and modify own characteristics
 * No more than on item of each EquipmentType can be put on
 */
class Inventory(val owner: Hero) {
    val equipped = HashMap<EquipmentType, Equipment>()
    val inPossesionOf = HashSet<Loot>()
    var selected: Loot? = null

    /**
     * Adds item to inventory
     * */
    fun addToInventory(item: Loot) {
        inPossesionOf.add(item)
        if (selected == null) {
            selected = inPossesionOf.firstOrNull()
        }
    }

    /**
     * Removes item from inventory
     * */
    fun removeFromInventory(item: Loot) {
        if (item is Equipment && equipped[item.type] == item) {
            equip(EmptyEquipment(item.type))
        }
        inPossesionOf.remove(item)
        if (selected == item) {
            selected = inPossesionOf.firstOrNull()
        }
    }

    /**
     * Selects given item
     * */
    fun selectItem(item: Loot) {
        if (item in inPossesionOf) {
            selected = item
        }
    }

    /**
     * Returns loot
     * */
    fun getLoot(): List<Loot> {
        return inPossesionOf.toList().sortedBy { loot -> loot.getDescrition() }
    }

    /**
     * Selects previous loot
     * */
    fun selectPreviousLoot() {
        var previousLoot: Loot? = null
        for (loot in inPossesionOf) {
            if (loot == selected) {
                break
            }
            previousLoot = loot
        }
        selected = previousLoot ?: selected
    }

    /**
     * Selects next loot
     * */
    fun selectNextLoot() {
        if (selected == null) {
            return
        }
        var previousLoot: Loot? = null
        for (loot in inPossesionOf) {
            if (previousLoot == selected) {
                selected = loot
                return
            }
            previousLoot = loot
        }
    }

    /**
     * Uses selected loot
     * */
    fun useSelected() {
        selected?.use(this)
    }

    /**
     * Equips new item
     * */
    fun equip(newItem: Equipment) {
        equipped[newItem.type]?.removeBuffs(owner)
        equipped[newItem.type] = newItem
        equipped[newItem.type]?.addBuffs(owner)
    }

    /**
     * Checks that item is equipped
     * */
    fun isEquipped(loot: Loot?): Boolean {
        return equipped.values.contains(loot)
    }

    /**
     * Serializes inventory
     * */
    fun serialize() = InventoryMemento.serialize(this)

    companion object {
        /**
         * Deserializes inventory
         * */
        fun deserialize(line: String, owner: Hero): Inventory? {
            return InventoryMemento.deserialize(line, owner)
        }
    }
}