package inc.roguelike.babusya.map.rectangularMap

import inc.roguelike.babusya.gameElement.EmptyGameElement
import inc.roguelike.babusya.map.Cell
import org.junit.Assert.assertEquals
import org.junit.Test


class RectangularMapTest {

    private val map = RectangularMap(Array(5) {Array(5) { Cell(EmptyGameElement()) } })

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
}
