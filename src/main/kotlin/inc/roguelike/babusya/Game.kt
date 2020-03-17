package inc.roguelike.babusya

import InputListener
import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.gameElement.Creature
import inc.roguelike.babusya.levels.LevelCreator

/**
 * Initializes and starts game
 */
class Game(renderSystem: RenderSystem, inputListener: InputListener) {

    private val engine = Engine(renderSystem, ActionSystem())
    private val levelCreator = LevelCreator(inputListener)
    private val gameState = GameState(levelCreator)

    fun launch() {
        for (cell in gameState.getLevel().getMap()) {
            if (cell.storesActiveItem() && cell.storedItem is Creature) {
                engine.actionSystem.addElement(cell.storedItem as Creature)
            }
        }
        while (!gameState.didGameEnd()) {
            engine.tick(gameState)
        }
    }

    fun endGame() {
        gameState.endGame()
    }
}
