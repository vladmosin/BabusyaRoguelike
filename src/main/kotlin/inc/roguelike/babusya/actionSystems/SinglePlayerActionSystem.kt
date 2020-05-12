package inc.roguelike.babusya.actionSystems

class SinglePlayerActionSystem: ActionSystem() {
    override fun action() {
        val elem = queue.pollFirst()
        if (elem != null && elem.isActive()) {
            action(elem)
        }
    }
}