package inc.roguelike.babusya.loot.memento

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.loot.*

/**
 * Memento implementation for inventory
 * */
class InventoryMemento {
    companion object {
        private const val name = "Inventory"
        private const val mapName = "Map"
        private const val keyValueName = "KeyValue"

        /**
         * Deserializes memento
         * */
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

                    val map = deserializeMap(args[itemsNumber + 1], items) ?: return null
                    val selectedIndex = args.last().toInt()
                    val selected = if (selectedIndex == -1) null else items[selectedIndex]
                    val inventory = Inventory(owner)

                    for (keyValue in map) {
                        inventory.equipped[keyValue.key] = keyValue.value
                    }
                    inventory.inPossesionOf.addAll(items)
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
                if (item.value is EmptyEquipment) {
                    continue
                }
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

        /**
         * Serializes inventory
         * */
        fun serialize(inventory: Inventory): String {
            val items = ArrayList(inventory.inPossesionOf)
            val args = ArrayList<String>()

            args.add(items.size.toString())
            for (item in items) {
                args.add(item.serialize())
            }

            args.add(serializeMap(inventory.equipped, items))
            if (inventory.selected == null) {
                args.add("-1")
            } else {
                args.add(items.indexOf(inventory.selected).toString())
            }

            return collectToString(name, args)
        }
    }
}