package inc.roguelike.babusya

class Engine(val renderSystem: RenderSystem, val actionSystem: ActionSystem) {

    fun tick(gameState: GameState) {
        renderSystem.render()
        actionSystem.action()

    }
}