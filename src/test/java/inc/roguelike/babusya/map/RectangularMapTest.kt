package inc.roguelike.babusya.map

import InputListener
import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.map.rectangularMap.RectangularMap
import inc.roguelike.babusya.map.rectangularMap.RectangularMapBuilder
import org.junit.jupiter.api.Test

internal class RectangularMapTest {
    private class TestInputListener: InputListener {
        override fun addCommand(command: (InputData) -> Unit): Int {
            return 0
        }

        override fun removeCommand(id: Int) {}

    }

    @Test
    fun serialize() {
        val inputListener = TestInputListener()
        val map = RectangularMapBuilder(3, 3).
            addHero().
            addWalls().
            buildMap(inputListener)

        val serializedMap = map.serialize()
        val newMap = RectangularMap.deserialize(serializedMap, inputListener)
    }
}