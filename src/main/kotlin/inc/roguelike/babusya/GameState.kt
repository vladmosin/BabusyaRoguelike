package inc.roguelike.babusya

import inc.roguelike.babusya.levels.Level
import inc.roguelike.babusya.levels.LevelCreator

/**
 * Keeps information about current game
 */
class GameState(private val levelCreator: LevelCreator) {
    private var level: Level
    private var didGameEnd = false

    init {
        level = levelCreator.createLevel(1)
    }

    /**
     * Initializes new level
     */
    fun startNewLevel() {
        level = levelCreator.createLevel(level.getId() + 1)
    }

    fun endGame() {
        didGameEnd = true
    }

    fun didGameEnd(): Boolean = didGameEnd

    fun getLevel(): Level = level
}