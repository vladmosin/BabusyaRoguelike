package inc.roguelike.babusya.engines

import inc.roguelike.babusya.GameState
import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.actionSystems.SinglePlayerActionSystem

/**
 * Launches game systems
 */
class SinglePlayerEngine(val renderSystem: RenderSystem,
                         val actionSystem: SinglePlayerActionSystem): Engine {

    override fun tick(gameState: GameState) {
        renderSystem.render(gameState.getLevel(), gameState.gameLog)
        actionSystem.action()
    }

    override fun actionSystem() = actionSystem
}
