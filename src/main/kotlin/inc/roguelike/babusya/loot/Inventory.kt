package inc.roguelike.babusya.loot

import inc.roguelike.babusya.element.concrete.Hero

class Inventory(val owner: Hero) {
    val equipped = HashMap<EquipmentType, Equipment>()
    val inPossessionOf = HashSet<Loot>()
    var selected: Loot? = null

    init {
        for (type in EquipmentType.values()) {
            equipped[type] = EmptyEquipment(type)
        }
    }

    fun addToInventory(item: Loot) {
        inPossessionOf.add(item)
    }
    
    fun removeFromInventory(item: Loot) {
        if (item is Equipment && equipped[item.type] == item) {
            equip(EmptyEquipment(item.type))
        }
        inPossessionOf.remove(item)
    }

    fun selectItem(item: Loot) {
        if (item in inPossessionOf) {
            selected = item
        }
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
}
