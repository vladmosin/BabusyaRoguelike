package inc.roguelike.babusya

import inc.roguelike.babusya.gameElement.Creature
import inc.roguelike.babusya.gameElement.GameElement
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
        for (elem in queue) {
            elem.makeTurn()
        }
    }
}