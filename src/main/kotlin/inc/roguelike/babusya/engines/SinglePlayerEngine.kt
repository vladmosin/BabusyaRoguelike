package inc.roguelike.babusya.engines

import inc.roguelike.babusya.GameState
import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.actionSystems.SinglePlayerActionSystem

/**
 * Launches game systems
 */
class SinglePlayerEngine(val renderSystem: RenderSystem,
                         val actionSystem: SinglePlayerActionSystem): Engine {

    /**
     * Processes one step for single player game
     * */
    override fun tick(gameState: GameState) {
        renderSystem.render(gameState.getLevel(), gameState.gameLog)
        actionSystem.action()
    }

    /**
     * Returns action system
     * */
    override fun actionSystem() = actionSystem
}
