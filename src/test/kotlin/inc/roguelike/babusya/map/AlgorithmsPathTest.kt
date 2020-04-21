package inc.roguelike.babusya.map

import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.Wall
import inc.roguelike.babusya.map.rectangularMap.RectangularMap
import org.junit.Assert.*
import org.junit.Test

class AlgorithmsPathTest {

    @Test
    fun shortestPathTest() {
        val rectangle = Array(2) {Array(5) { Cell() } }
        rectangle[0][1].storedItem = Wall("w1", ElementStatus.ALIVE)
        rectangle[0][2].storedItem = Wall("w2", ElementStatus.ALIVE)
        rectangle[0][3].storedItem = Wall("w3", ElementStatus.ALIVE)
        val map = RectangularMap(rectangle)

        val path = shortestPath(map, rectangle[0][0], rectangle[0][4])
        assertEquals(path, listOf(rectangle[0][0], rectangle[1][0], rectangle[1][1],
            rectangle[1][2], rectangle[1][3], rectangle[1][4], rectangle[0][4]))
    }

    @Test
    fun shortestPathWhenNoPathExistsTest() {
        val rectangle = Array(2) {Array(5) { Cell() } }
        rectangle[0][2].storedItem = Wall("w1", ElementStatus.ALIVE)
        rectangle[1][2].storedItem = Wall("w2", ElementStatus.ALIVE)

        val map = RectangularMap(rectangle)

        val path = shortestPath(map, rectangle[0][0], rectangle[0][4])
        assertNull(path)
    }
}
