package inc.roguelike.babusya.map

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
