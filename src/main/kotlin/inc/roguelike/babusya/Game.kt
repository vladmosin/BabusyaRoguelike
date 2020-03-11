package inc.roguelike.babusya

import inc.roguelike.babusya.gameElement.ElementStatus
import inc.roguelike.babusya.gameElement.GameElement
import inc.roguelike.babusya.gameElement.Wall
import java.util.*

class Game {

    private val engine = Engine(RenderSystem(Arrays.asList(Wall("lol", ElementStatus.ALIVE))), ActionSystem())
    private val gameState = GameState()

    fun launch() {
        while (!gameState.didGameEnd()) {
            engine.tick(gameState)
        }
    }
}