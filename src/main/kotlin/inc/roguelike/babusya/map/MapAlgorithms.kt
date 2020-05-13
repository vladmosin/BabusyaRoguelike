package inc.roguelike.babusya.map

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Implementation of algorithms on game maps
 * */



/**
 * Finds shortest path between two cells
 * */
fun shortestPath(map: GameMap, from: Cell, to: Cell) : List<Cell>? {
    val p = HashMap<Cell?, Cell?>()
    val q: Queue<Cell> = LinkedList()

    fun addToQueue(from: Cell, to: Cell?) {
        if (to == null || to in p) {
            return
        }
        p[to] = from
        if (!to.storesActiveItem())
            q.add(to)
    }

    p[from] = null
    q.add(from)
    while (q.isNotEmpty()) {
        val v = q.poll()
        if (v == to) break

        addToQueue(v, map.getRighterCell(v))
        addToQueue(v, map.getUpperCell(v))
        addToQueue(v, map.getLefterCell(v))
        addToQueue(v, map.getDownerCell(v))
    }

    if (to !in p) {
        return null
    }

    var now: Cell? = to
    val path = ArrayList<Cell>()

    while (now != null) {
        path.add(now)
        now = p[now]
    }
    return path.reversed()
}