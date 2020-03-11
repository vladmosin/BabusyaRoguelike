package inc.roguelike.babusya

import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.levels.LevelCreator

class Game(renderSystem: RenderSystem, levelCreator: LevelCreator) {

    private val engine = Engine(renderSystem, ActionSystem())
    private val gameState = GameState(levelCreator)

    fun launch() {
        while (!gameState.didGameEnd()) {
            engine.tick(gameState)
        }
    }
}