package inc.roguelike.babusya.map.rectangularMap

import InputListener
import inc.roguelike.babusya.FileSystem.Companion.saveToFile
import inc.roguelike.babusya.commands.AbstractCommand
import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.loot.Equipment
import inc.roguelike.babusya.loot.EquipmentType
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.GameMap
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test


class RectangularMapTest {
    private val map = RectangularMap(Array(5) {Array(5) { Cell() } })

    private class EmptyInputListener: InputListener {
        override fun addCommand(command: (AbstractCommand) -> Unit): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    private fun mapsEqual(map1: RectangularMap, map2: RectangularMap) {
        val rect1 = map1.getRectangle()
        val rect2 = map2.getRectangle()
        assertEquals(rect1.size, rect2.size)
        assertEquals(rect1[0].size, rect2[0].size)

        for (i in rect1.indices) {
            for (j in rect1[0].indices) {
                assertEquals(rect1[i][j].storedItem.javaClass, rect2[i][j].storedItem.javaClass)
            }
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
        for (i in 0..0) {
            val map = RectangularMapBuilder(10, 20)
                .addRandomWalls(0.95)
                .addBasicMonsters()
                .addLootRandomly(Equipment(EquipmentType.HAT, 100, 100))
                .buildMap(EmptyInputListener())

            val serialized = map.serialize()
            val deserialized = RectangularMap.deserialize(serialized, EmptyInputListener())
            assertNotNull(deserialized)
            mapsEqual(map, deserialized!!)
        }
    }
}
