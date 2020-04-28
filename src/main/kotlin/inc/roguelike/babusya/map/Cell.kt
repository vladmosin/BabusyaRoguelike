package inc.roguelike.babusya.map

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.concrete.EmptyGameElement
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.loot.Loot

/**
 * Implement a cell for game map.
 * Stores one game element.
 * */
class Cell {
    private val observers = ArrayList<CellObserver>()

    var storedItem: GameElement = EmptyGameElement()
        set(value) {
            field = value
            for (observer in observers)
                observer.onCellUpdate(this)
        }

    var loot: Loot? = null

    fun storesActiveItem(): Boolean = storedItem.isActive()

    fun attachObserver(cellObserver: CellObserver) {
        observers.add(cellObserver)
    }

    fun serialize(): String {
        return collectToString(name, listOf(storedItem.serialize(), if (loot == null) "" else loot!!.serialize()))
    }

    fun clone(): Cell {
        val newCell = Cell()
        newCell.storedItem = storedItem.clone()

        return newCell
    }

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
    }


}
