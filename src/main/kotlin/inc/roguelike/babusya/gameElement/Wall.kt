package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.visitors.Visitor

/**
 * Implements wall.
 * Creatures cannot walk through the wall.
 * */
class Wall(id: String, elementStatus: ElementStatus) : StaticElement(id, elementStatus) {
    override fun <T> accept(visitor: Visitor<T>): T {
        return visitor.visitWall(this)
    }

    override fun isActive(): Boolean {
        return elementStatus == ElementStatus.ALIVE
    }

    override fun serialize(): String {
        return "${name}#${id}#${elementStatus}"
    }

    companion object {
        fun deserialize(string: String): Wall? {
            val parts = string.split("#")
            return if (parts.size != 3) {
                null
            } else {
                val elementStatus = ElementStatus.deserialize(parts[2])
                if (elementStatus == null || parts[0] != name) {
                    null
                } else {
                    Wall(parts[1], elementStatus)
                }
            }
        }

        private const val name = "w"
    }
}
