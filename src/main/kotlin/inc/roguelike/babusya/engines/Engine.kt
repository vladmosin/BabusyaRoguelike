package inc.roguelike.babusya.engines

import inc.roguelike.babusya.GameState
import inc.roguelike.babusya.actionSystems.ActionSystem

interface Engine {
    fun tick(gameState: GameState)

    fun actionSystem(): ActionSystem
}