package inc.roguelike.babusya.actionSystems

import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.network.PlayersHolder
import java.util.*

class MultiplayerActionSystem: ActionSystem() {
    val playersHolder = PlayersHolder()

    override fun action() {
        val elem = queue.pollFirst()
        if (elem != null && elem.isActive() && creatureOwnerActive(elem)) {
            action(elem)
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