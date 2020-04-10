package inc.roguelike.babusya.effects

import inc.roguelike.babusya.gameElement.GameElement

interface Effect {
    fun apply(gameElement: GameElement)
    fun getDescription(from: GameElement?, to: GameElement?): String?
}