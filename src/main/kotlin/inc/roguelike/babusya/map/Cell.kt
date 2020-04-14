package inc.roguelike.babusya.map

import inc.roguelike.babusya.element.concrete.EmptyGameElement
import inc.roguelike.babusya.element.interfaces.GameElement

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

    fun storesActiveItem(): Boolean = storedItem.isActive()

    fun attachObserver(cellObserver: CellObserver) {
        observers.add(cellObserver)
    }

    fun serialize(): String = storedItem.serialize()
}
