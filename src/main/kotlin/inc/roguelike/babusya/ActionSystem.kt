package inc.roguelike.babusya

import inc.roguelike.babusya.element.interfaces.Creature
import java.util.*

/**
 * Asks every element in queue to make turn
 */
class ActionSystem {

    private val queue: Queue<Creature> = LinkedList<Creature>()

    fun addElement(creature: Creature) {
        queue.add(creature)
    }

    fun action() {
        val elem = queue.poll()
        if (elem != null && elem.isActive()) {
            elem.makeTurn()
            queue.add(elem)
        }
    }
}
