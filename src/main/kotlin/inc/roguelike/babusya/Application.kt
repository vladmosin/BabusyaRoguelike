package inc.roguelike.babusya

import inc.roguelike.babusya.UI.ConsoleRenderSystem
import inc.roguelike.babusya.levels.LevelCreator

fun main() {
    val renderSystem = ConsoleRenderSystem()
    val levelCreator = LevelCreator()
    Game(renderSystem, levelCreator).launch()
}