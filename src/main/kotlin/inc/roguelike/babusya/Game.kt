package inc.roguelike.babusya

import InputListener
import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.engines.Engine
import inc.roguelike.babusya.engines.SinglePlayerEngine
import inc.roguelike.babusya.levels.LevelCreator
import inc.roguelike.babusya.levels.LevelInfo
import java.util.concurrent.atomic.AtomicInteger

/**
 * Initializes and starts game
 */
class Game(val inputListener: InputListener,
           val engine: Engine, levelInfo: LevelInfo) {

    var tick = AtomicInteger(1)

    companion object {
        const val SAVED_LEVELS = 2
    }

    private val levelCreator = LevelCreator(inputListener)
    val gameState = GameState(levelCreator, levelInfo)
    private val deathObserver = DeathObserver(gameState.getLevel().getMap())

    init {
        deathObserver.observe(gameState.getLevel().getMap())
    }

    /**
     * Launches new game
     * */
    fun launch() {
        println("GAME LAUNCHED")

        for (cell in gameState.getLevel().getMap()) {
            if (cell.storesActiveItem() && cell.storedItem is Creature) {
                engine.actionSystem().addElement(cell.storedItem as Creature)
                (cell.storedItem as Creature).actionController?.useLog(gameState.gameLog)

                if (cell.storedItem is Hero) {
                    gameState.focusedHero = cell.storedItem as Hero
                }
            }
        }

        println("DID GAME END = ${gameState.didGameEnd()}")

        while (!gameState.didGameEnd()) {
            println("tick in while")
            tick.incrementAndGet()
            engine.tick(gameState)
        }

        println("AFTER WHILE")
    }

    /**
     * Ends game
     * */
    fun endGame() {
        gameState.endGame()
    }

    /**
     * Resumes game
     * */
    fun resume() {
        gameState.resume()
    }
}
