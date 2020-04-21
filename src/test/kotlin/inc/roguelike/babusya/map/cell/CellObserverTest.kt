package inc.roguelike.babusya.map.cell

import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.Wall
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.CellObserver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test

class CellObserverTest {

    class saveAllUpdatesObserver: CellObserver {
        val updates = arrayListOf<GameElement>()
        override fun onCellUpdate(cell: Cell) {
            updates.add(cell.storedItem)
        }
    }

    @Test
    fun simpleUpdates() {
        val cell = Cell()
        val observer = saveAllUpdatesObserver()

        val wall1 = Wall("w1", ElementStatus.ALIVE)
        val wall2 = Wall("w2", ElementStatus.ALIVE)

        cell.storedItem = wall1
        cell.storedItem = wall2
        cell.attachObserver(observer)
        cell.storedItem = wall1
        cell.attachObserver(observer)
        cell.storedItem = wall2

        assertEquals(observer.updates, listOf(wall1, wall2, wall2))
    }

}
