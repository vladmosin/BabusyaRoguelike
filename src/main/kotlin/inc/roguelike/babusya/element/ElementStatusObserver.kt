package inc.roguelike.babusya.element

import inc.roguelike.babusya.element.interfaces.GameElement

interface ElementStatusObserver {
    fun onStatusUpdate(gameElement: GameElement)
}
