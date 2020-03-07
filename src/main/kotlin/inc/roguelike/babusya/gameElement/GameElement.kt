package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.Visitor

interface GameElement {
    fun <T> accept(visitor: Visitor<T>): T
    fun act(gameElement: GameElement)
    fun bePunched(damage: Int)
}