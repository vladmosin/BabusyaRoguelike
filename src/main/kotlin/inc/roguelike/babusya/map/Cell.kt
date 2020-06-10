package inc.roguelike.babusya.map

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.concrete.EmptyGameElement
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.loot.Loot
import inc.roguelike.babusya.map.memento.CellMemento

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

    /**
     * Checks that storing element is active
     * */
    fun storesActiveItem(): Boolean = storedItem.isActive()

    /**
     * Attaches observer
     * */
    fun attachObserver(cellObserver: CellObserver) {
        observers.add(cellObserver)
    }

    /**
     * Serializes cell
     * */
    fun serialize() = CellMemento.serialize(this)

    /**
     * Clones cell
     * */
    fun clone(): Cell {
        val newCell = Cell()
        newCell.storedItem = storedItem.clone()

        return newCell
    }

    companion object {
        /**
         * Deserializes cell
         * */
        fun deserialize(controllerFactory: ControllerFactory, line: String): Cell? {
            return CellMemento.deserialize(controllerFactory, line)
        }
    }
}
