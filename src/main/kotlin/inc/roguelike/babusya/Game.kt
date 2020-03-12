package inc.roguelike.babusya

import InputListener
import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.levels.LevelCreator

class Game(renderSystem: RenderSystem, val inputListener: InputListener, levelCreator: LevelCreator) {

    private val engine = Engine(renderSystem, ActionSystem())
    private val gameState = GameState(levelCreator)

    fun launch() {
        while (!gameState.didGameEnd()) {
            engine.tick(gameState)
        }
    }

    fun endGame() {
        gameState.endGame()
    }
}