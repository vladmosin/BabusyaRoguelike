package inc.roguelike.babusya.engines

import inc.roguelike.babusya.GameState
import inc.roguelike.babusya.actionSystems.ActionSystem

/**
 * Interface for game engine
 * */
interface Engine {
    /**
     * Gets gameState as argument and processes one game step
     * */
    fun tick(gameState: GameState)

    /**
     * Returns action system
     * */
    fun actionSystem(): ActionSystem
}