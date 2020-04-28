package inc.roguelike.babusya.loot

import inc.roguelike.babusya.element.concrete.Hero

class Inventory(val owner: Hero) {
    val equipped = HashMap<EquipmentType, Equipment>()
    val inPossesionOf = HashSet<Loot>()
    var selected: Loot? = null

    init {
        for (type in EquipmentType.values()) {
            equipped[type] = EmptyEquipment(type)
        }
    }

    fun addToInventory(item: Loot) {
        inPossesionOf.add(item)
    }
    
    fun removeFromInventory(item: Loot) {
        if (item is Equipment && equipped[item.type] == item) {
            equip(EmptyEquipment(item.type))
        }
        inPossesionOf.remove(item)
    }

    fun selectItem(item: Loot) {
        if (item in inPossesionOf) {
            selected = item
        }
    }

    fun selectPreviousLoot() {
        var previousLoot: Loot? = null
        for (loot in inPossesionOf) {
            if (loot == selected) {
                break
            }
            previousLoot = loot
        }
        selected = previousLoot
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
        selected = null
    }

    fun useSelected() {
        selected?.use(this)
        selected = null
    }

    fun equip(newItem: Equipment) {
        equipped[newItem.type]?.removeBuffs(owner)
        equipped[newItem.type] = newItem
        equipped[newItem.type]?.addBuffs(owner)
    }

    fun isEquipped(loot: Loot?): Boolean {
        return equipped.values.contains(loot)
    }
}
