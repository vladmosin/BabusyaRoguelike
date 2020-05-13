package inc.roguelike.babusya.actionSystems

import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.network.PlayersHolder
import java.util.*

/**
 * Asks every element in queue to make turn
 */
abstract class ActionSystem {
    protected val queue: Deque<Creature> = LinkedList<Creature>()

    /**
     * Adds element to the end of turn queue
     * */
    fun addElement(creature: Creature) {
        queue.add(creature)
    }

    protected fun action(elem: Creature) {
        if (elem.makeTurn()) {
            queue.add(elem)
        } else {
            queue.addFirst(elem)
        }
    }

    /**
     * Selects creature and performs its action
     * */
    abstract fun action()
}
