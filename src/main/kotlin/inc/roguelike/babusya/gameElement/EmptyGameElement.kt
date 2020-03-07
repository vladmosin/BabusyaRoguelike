package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.Visitor

class EmptyGameElement() : GameElement(".", ElementStatus.ALIVE) {
    override fun <T> accept(visitor: Visitor<T>): T {
        return visitor.visitEmptyGameElement(this)
    }

    override fun act(gameElement: GameElement) {}

    override fun bePunched(damage: Int) {}
}