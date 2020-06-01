package inc.roguelike.babusya

import inc.roguelike.babusya.element.ElementStatusObserver
import inc.roguelike.babusya.element.concrete.EmptyGameElement
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.GameMap

/**
 * Observes that game element is active
 * */
class DeathObserver(val map: GameMap): ElementStatusObserver {
    init {
        for (cell in map) {
            if (cell.storesActiveItem()) {
                cell.storedItem.attachStatusObserver(this)
            }
        }
    }

    /**
     * Pin observer
     * */
    override fun onStatusUpdate(gameElement: GameElement) {
        map.getCellByElement(gameElement)?.storedItem = EmptyGameElement()
    }
}
