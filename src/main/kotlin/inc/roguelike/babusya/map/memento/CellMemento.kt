package inc.roguelike.babusya.map.memento

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.loot.Loot
import inc.roguelike.babusya.map.Cell

class CellMemento {
    companion object {
        private const val name = "Cell"

        fun deserialize(controllerFactory: ControllerFactory, line: String): Cell? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.name || args.size != 2) {
                null
            } else {
                val item = GameElement.deserialize(controllerFactory, args[0])
                val loot = Loot.deserialize(args[1])

                if (item == null) {
                    null
                } else {
                    val cell = Cell()
                    cell.loot = loot
                    cell.storedItem = item

                    cell
                }
            }
        }

        fun serialize(cell: Cell): String {
            return collectToString(name, listOf(cell.storedItem.serialize(),
                if (cell.loot == null) "null" else cell.loot!!.serialize()))
        }
    }
}