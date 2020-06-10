package inc.roguelike.babusya

import inc.roguelike.babusya.element.interfaces.Creature
import java.util.*

/**
 * Asks every element in queue to make turn
 */
class ActionSystem {

    private val queue: Deque<Creature> = LinkedList<Creature>()

    /**
     * Adds elemens to observe
     * */
    fun addCreature(creature: Creature) {
        queue.add(creature)
    }

    /**
     * Processes action
     * */
    fun action() {
        val elem = queue.pollFirst()
        if (elem != null && elem.isActive()) {
            if (elem.makeTurn()) {
                queue.add(elem)
            } else {
                queue.addFirst(elem)
            }
        }
    }
}
