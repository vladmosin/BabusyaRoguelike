package inc.roguelike.babusya

import inc.roguelike.babusya.gameElement.GameElement
import java.util.*

/**
 * Asks every element in queue to make turn
 */
class ActionSystem {

    private val queue: Queue<GameElement> = LinkedList<GameElement>()

    fun addElement(element: GameElement) {
        queue.add(element)
    }

    fun action() {
        for (elem in queue) {
            elem.makeTurn()
        }
    }
}