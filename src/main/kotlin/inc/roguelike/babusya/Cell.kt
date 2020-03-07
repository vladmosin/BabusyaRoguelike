package inc.roguelike.babusya

import inc.roguelike.babusya.gameElement.EmptyGameElement
import inc.roguelike.babusya.gameElement.GameElement

/**
 * Implement a cell for game map.
 * Stores one game element.
 * */
class Cell(var storedItem: GameElement) {
    fun storesActiveItem(): Boolean {
        return storedItem.isActive()
    }
}