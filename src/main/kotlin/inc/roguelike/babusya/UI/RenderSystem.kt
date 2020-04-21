package inc.roguelike.babusya.UI

import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.GameState
import inc.roguelike.babusya.levels.Level

/**
 * Interface for system renders
 * */
interface RenderSystem {
    /**
     * Renders level using given log
     * */
    fun render(level: Level, gameLog: GameLog)
}