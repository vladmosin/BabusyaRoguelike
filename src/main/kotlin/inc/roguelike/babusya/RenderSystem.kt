package inc.roguelike.babusya

import inc.roguelike.babusya.gameElement.GameElement
import inc.roguelike.babusya.visitors.ShowConsoleVisitor
import java.util.*

class RenderSystem(elements: Collection<GameElement>) {

    private val queue: Queue<GameElement> = LinkedList<GameElement>(elements)
    private val showVisitor = ShowConsoleVisitor()

    fun render() {
        for (element in queue) {
            val char = element.accept(showVisitor)
            println(char)
        }

    }
}