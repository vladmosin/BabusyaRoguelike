package inc.roguelike.babusya.effects

import inc.roguelike.babusya.gameElement.GameElement

interface Effect {
    fun apply(gameElement: GameElement)
}