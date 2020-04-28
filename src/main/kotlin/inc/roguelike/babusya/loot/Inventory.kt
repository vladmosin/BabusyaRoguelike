package inc.roguelike.babusya.loot

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName

class Inventory(val owner: Hero) {
    val equipped = HashMap<EquipmentType, Equipment>()
    val inPossessionOf = HashSet<Loot>()
    var selected: Loot? = null

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

    fun serialize(): String {
        val items = ArrayList(inPossessionOf)
        val args = ArrayList<String>()

        args.add(items.size.toString())
        for (item in items) {
            args.add(item.serialize())
        }

        args.add(serializeMap(equipped, items))
        if (selected == null) {
            args.add("-1")
        } else {
            args.add(items.indexOf(selected).toString())
        }

        return collectToString(name, args)
    }

    companion object {
        private const val name = "Inventory"
        private const val mapName = "Map"
        private const val keyValueName = "KeyValue"

        fun deserialize(line: String, owner: Hero): Inventory? {
            val name = getName(line)
            val args = getArguments(line)
            return if (name == null || args == null || name != this.name || args.size < 2) {
                null
            } else {
                try {
                    val itemsNumber = args[0].toInt()
                    val items = ArrayList<Loot>()
                    for (i in 1..itemsNumber) {
                        val item = Loot.deserialize(args[i]) ?: return null
                        items.add(item)
                    }

                    val map = deserializeMap(line, items) ?: return null
                    val selectedIndex = args.last().toInt()
                    val selected = if (selectedIndex == -1) null else items[selectedIndex]
                    val inventory = Inventory(owner)

                    for (keyValue in map) {
                        inventory.equipped[keyValue.key] = keyValue.value
                    }
                    inventory.inPossessionOf.addAll(items)
                    inventory.selected = selected

                    return inventory

                } catch (e: NumberFormatException) {
                    null
                }
            }
        }

        private fun serializeMap(equipped: Map<EquipmentType, Equipment>, items: ArrayList<Loot>): String {
            val args = ArrayList<String>()
            for (item in equipped) {
                val index = items.indexOf(item.value)
                assert(index > -1)
                args.add(collectToString(keyValueName, listOf(item.key.name, index.toString())))
            }

            return collectToString(mapName, args)
        }

        private fun deserializeMap(line: String, items: ArrayList<Loot>): HashMap<EquipmentType, Equipment>? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.mapName) {
                null
            } else {
                val map = HashMap<EquipmentType, Equipment>()
                for (arg in args) {
                    val keyValue = deserializeKeyValue(arg) ?: return null
                    map[keyValue.first] = items[keyValue.second] as Equipment
                }

                map
            }
        }

        private fun deserializeKeyValue(line: String): Pair<EquipmentType, Int>? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.keyValueName || args.size != 2) {
                null
            } else {
                try {
                    val type = EquipmentType.deserialize(args[0]) ?: return null
                    Pair(type, args[1].toInt())
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }
    }
}
