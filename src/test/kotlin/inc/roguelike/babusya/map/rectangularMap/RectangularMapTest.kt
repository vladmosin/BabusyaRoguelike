package inc.roguelike.babusya.map.rectangularMap

import InputListener
import inc.roguelike.babusya.FileSystem.Companion.saveToFile
import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.map.Cell
import org.junit.Assert.assertEquals
import org.junit.Test


class RectangularMapTest {
    private val map = RectangularMap(Array(5) {Array(5) { Cell() } })

    private class EmptyInputListener: InputListener {
        override fun addCommand(command: (InputData) -> Unit): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    @Test
    fun testBorders() {
        for (i in 0 until 5) {
            for (j in 0 until 5) {
                val cell = map.getRectangle()[i][j]
                assertEquals(j == 0, map.getLefterCell(cell) == null)
                assertEquals(j == 4, map.getRighterCell(cell) == null)
                assertEquals(i == 0, map.getUpperCell(cell) == null)
                assertEquals(i == 4, map.getDownerCell(cell) == null)
            }
        }
    }

    @Test
    fun testClone() {
        val map = RectangularMapBuilder(20, 30)
            .addRandomWalls()
            .addHero()
            .addRandomMonsters()
            .addRandomLoot()
            .buildMap(EmptyInputListener())

        val serialized = map.serialize()
        saveToFile("Levels/Level2", serialized)
    }
}
