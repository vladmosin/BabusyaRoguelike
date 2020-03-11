package inc.roguelike.babusya

import inc.roguelike.babusya.levels.Level
import inc.roguelike.babusya.levels.LevelCreator

class GameState(private val levelCreator: LevelCreator) {
    private lateinit var level: Level

    private var currentLevel = 0
    private var didGameEnd = false

    init {
        startNewLevel()
    }

    fun startNewLevel() {
        level = levelCreator.createLevel(++currentLevel)
    }

    fun endGame() {
        didGameEnd = true
    }

    fun didGameEnd(): Boolean = didGameEnd

    fun getLevel(): Level = level
}