package inc.roguelike.babusya

import inc.roguelike.babusya.UI.RenderSystem
import java.lang.Thread.sleep

/**
 * Launches game systems
 */
class Engine(val renderSystem: RenderSystem, val actionSystem: ActionSystem) {

    fun tick(gameState: GameState) {
        renderSystem.render(gameState.getLevel())
        actionSystem.action()
    }
}