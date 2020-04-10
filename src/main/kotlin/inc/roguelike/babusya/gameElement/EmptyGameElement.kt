package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.visitors.Visitor

/**
 * If cell doesn't store real game object, than it stores EmptyGameElement
 * */
class EmptyGameElement : StaticElement(name, ElementStatus.ALIVE) {
    override fun <T> accept(visitor: Visitor<T>): T {
        return visitor.visitEmptyGameElement(this)
    }

    override fun isActive(): Boolean {
        return false
    }

    override fun serialize(): String {
        return name
    }

    companion object {
        fun deserialize(string: String): EmptyGameElement? {
            return if (string == name) {
                EmptyGameElement()
            } else {
                null
            }
        }

        private const val name = "e"
    }
}