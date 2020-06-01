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

    fun addToInventory(item: Loot) {
        inPossesionOf.add(item)
        if (selected == null) {
            selected = inPossesionOf.firstOrNull()
        }
    }

    fun removeFromInventory(item: Loot) {
        if (item is Equipment && equipped[item.type] == item) {
            equip(EmptyEquipment(item.type))
        }
        inPossesionOf.remove(item)
        if (selected == item) {
            selected = inPossesionOf.firstOrNull()
        }
    }

    fun selectItem(item: Loot) {
        if (item in inPossesionOf) {
            selected = item
        }
    }

    fun getLoot(): List<Loot> {
        return inPossesionOf.toList().sortedBy { loot -> loot.getDescrition() }
    }

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

    fun useSelected() {
        selected?.use(this)
    }

    fun equip(newItem: Equipment) {
        equipped[newItem.type]?.removeBuffs(owner)
        equipped[newItem.type] = newItem
        equipped[newItem.type]?.addBuffs(owner)
    }

    fun isEquipped(loot: Loot?): Boolean {
        return equipped.values.contains(loot)
    }

    fun serialize() = InventoryMemento.serialize(this)

    companion object {
        fun deserialize(line: String, owner: Hero): Inventory? {
            return InventoryMemento.deserialize(line, owner)
        }
    }
}