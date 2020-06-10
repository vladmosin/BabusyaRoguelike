package inc.roguelike.babusya.element

import inc.roguelike.babusya.element.interfaces.GameElement

/**
 * Interface for observers of status of concrete game element
 * */
interface   ElementStatusObserver {
    /**
     * Processes action on gameElement
     * */
    fun onStatusUpdate(gameElement: GameElement)
}
