package inc.roguelike.babusya

import InputListener
import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.levels.LevelCreator
import inc.roguelike.babusya.levels.LevelInfo
import inc.roguelike.babusya.network.PlayersHolder

/**
 * Initializes and starts game
 */
class Game(renderSystem: RenderSystem, val inputListener: InputListener, levelInfo: LevelInfo) {

    companion object {
        const val SAVED_LEVELS = 2
    }

    private val levelCreator = LevelCreator(inputListener)
    private val gameState = GameState(levelCreator, levelInfo)
    private val engine = Engine(renderSystem, ActionSystem())
    private val deathObserver = DeathObserver(gameState.getLevel().getMap())

    /**
     * Launches new game
     * */
    fun launch() {
        for (cell in gameState.getLevel().getMap()) {
            if (cell.storesActiveItem() && cell.storedItem is Creature) {
                engine.actionSystem.addElement(cell.storedItem as Creature)
                (cell.storedItem as Creature).actionController?.useLog(gameState.gameLog)

                if (cell.storedItem is Hero) {
                    gameState.focusedHero = cell.storedItem as Hero
                }
            }
        }

        while (!gameState.didGameEnd()) {
            engine.tick(gameState)
        }
    }

    /**
     * Ends game
     * */
    fun endGame() {
        gameState.endGame()
    }
}
