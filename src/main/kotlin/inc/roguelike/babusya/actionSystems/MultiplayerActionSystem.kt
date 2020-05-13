package inc.roguelike.babusya.actionSystems

import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.network.PlayersHolder
import java.util.*

/**
 * Implementation of ActionSystem for multiplayer mode
 * */
class MultiplayerActionSystem: ActionSystem() {
    val playersHolder = PlayersHolder()

    /**
     * Selects creature and makes action
     * */
    override fun action() {
        println("ACTION queue size = ${queue.size}")
        val elem = queue.pollFirst()
        if (elem != null && elem.isActive() && creatureOwnerActive(elem)) {
            println("ENTER IF ${elem is Hero}")
            action(elem)
        }
        println("END ACTION")
    }

    private fun creatureOwnerActive(creature: Creature): Boolean {
        return if (creature !is Hero) {
            true
        } else {
            playersHolder.isPlayerActive(creature)
        }
    }
}
