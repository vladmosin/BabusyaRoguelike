package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.Visitor

abstract class GameElement(val id: String, var elementStatus: ElementStatus) {
    abstract fun <T> accept(visitor: Visitor<T>): T
    abstract fun act(gameElement: GameElement)
    abstract fun bePunched(damage: Int)
}