package inc.roguelike.babusya.map

import inc.roguelike.babusya.gameElement.EmptyGameElement
import inc.roguelike.babusya.gameElement.GameElement

/**
 * Implement a cell for game map.
 * Stores one game element.
 * */
class Cell {
    private val observers = ArrayList<CellObserver>()

    var storedItem: GameElement = EmptyGameElement()
        get() = field
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
