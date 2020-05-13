package inc.roguelike.babusya

import inc.roguelike.babusya.element.ElementStatusObserver
import inc.roguelike.babusya.element.concrete.EmptyGameElement
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.GameMap

/**
 * Observes that game element is active
 * */
class DeathObserver(val map: GameMap): ElementStatusObserver {

    fun observe(map: GameMap) {
        for (cell in map) {
            if (cell.storesActiveItem()) {
                cell.storedItem.attachStatusObserver(this)
            }
        }
    }

    fun observe(element: GameElement) {
        element.attachStatusObserver(this)
    }

    override fun onStatusUpdate(gameElement: GameElement) {
        map.getCellByElement(gameElement)?.storedItem = EmptyGameElement()
    }
}
