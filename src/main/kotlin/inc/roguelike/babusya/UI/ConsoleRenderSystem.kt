package inc.roguelike.babusya.UI

import inc.roguelike.babusya.levels.Level
import inc.roguelike.babusya.visitors.ShowConsoleVisitor

class ConsoleRenderSystem: RenderSystem {

    private val showVisitor = ShowConsoleVisitor()


    override fun render(level: Level) {
        println("NEW")

    }
}