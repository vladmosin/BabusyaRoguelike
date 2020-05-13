package inc.roguelike.babusya.actionSystems

/**
 * Implementation of ActionSystem for single player mode
 * */
class SinglePlayerActionSystem: ActionSystem() {

    /**
     * Selects creature and makes turn
     * */
    override fun action() {
        val elem = queue.pollFirst()
        if (elem != null && elem.isActive()) {
            action(elem)
        }
    }
}