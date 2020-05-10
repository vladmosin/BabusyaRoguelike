package inc.roguelike.babusya

import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.network.PlayersHolder
import java.util.*

/**
 * Asks every element in queue to make turn
 */
class ActionSystem {

    private val queue: Deque<Creature> = LinkedList<Creature>()
    val playersHolder = PlayersHolder()

    fun addElement(creature: Creature) {
        queue.add(creature)
    }

    fun action() {
        val elem = queue.pollFirst()
        playersHolder.removeDisconnectedPlayers()

        if (elem != null && elem.isActive() && creatureOwnerActive(elem)) {
            if (elem.makeTurn()) {
                queue.add(elem)
            } else {
                queue.addFirst(elem)
            }
        }
    }

    private fun creatureOwnerActive(creature: Creature): Boolean {
        return if (creature !is Hero) {
            true
        } else {
            playersHolder.isPlayerActive(creature)
        }
    }
}
