package inc.roguelike.babusya

import inc.roguelike.babusya.UI.RenderSystem
import java.lang.Thread.sleep

class Engine(val renderSystem: RenderSystem, val actionSystem: ActionSystem) {

    fun tick(gameState: GameState) {
        sleep(1000)
        renderSystem.render(gameState.getLevel())
        actionSystem.action()
    }
}