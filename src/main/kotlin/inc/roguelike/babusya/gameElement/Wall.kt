package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.Visitor

class Wall(id: String, elementStatus: ElementStatus) : StaticElement(id, elementStatus) {
    override fun <T> accept(visitor: Visitor<T>): T {
        return visitor.visitWall(this)
    }

    override fun act(gameElement: GameElement) {}

    override fun bePunched(damage: Int) {}
}