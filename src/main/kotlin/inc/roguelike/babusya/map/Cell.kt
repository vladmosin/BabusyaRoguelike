package inc.roguelike.babusya.map

import inc.roguelike.babusya.element.concrete.EmptyGameElement
import inc.roguelike.babusya.element.interfaces.GameElement
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

    val loot: Loot? = null

    fun storesActiveItem(): Boolean = storedItem.isActive()

    fun attachObserver(cellObserver: CellObserver) {
        observers.add(cellObserver)
    }

    fun serialize(): String = storedItem.serialize()

    fun clone(): Cell {
        val newCell = Cell()
        newCell.storedItem = storedItem.clone()

        return newCell
    }
}
