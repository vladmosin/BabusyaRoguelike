package inc.roguelike.babusya

import inc.roguelike.babusya.element.ElementStatusObserver
import inc.roguelike.babusya.element.concrete.EmptyGameElement
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.GameMap

class DeathObserver(val map: GameMap): ElementStatusObserver {
    init {
        for (cell in map) {
            if (cell.storesActiveItem()) {
                cell.storedItem.attachStatusObserver(this)
            }
        }
    }
    override fun onStatusUpdate(gameElement: GameElement) {
        map.getCellByElement(gameElement)?.storedItem = EmptyGameElement()
    }
}
