package inc.roguelike.babusya

import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.levels.Level
import inc.roguelike.babusya.levels.LevelCreator
import inc.roguelike.babusya.levels.LevelInfo
import inc.roguelike.babusya.levels.LevelsType

/**
 * Keeps information about current game
 */
class GameState(private val levelCreator: LevelCreator, private val levelInfo: LevelInfo) {
    private var level: Level
    private var didGameEnd = false
    var gameLog = GameLog()
    var focusedHero: Hero? = null

    init {
        level = levelCreator.createLevel(levelInfo.id, levelInfo.levelsType)
    }

    /**
     * Initializes new level
     */
    fun startNewLevel() {
        level = levelCreator.createLevel(level.getId() + 1, levelInfo.levelsType)
    }

    /**
     * Ends game
     * */
    fun endGame() {
        didGameEnd = true
    }

    /**
     * Resumes game
     * */
    fun resume() {
        didGameEnd = false
    }

    /**
     * Checks that game is finished
     * */
    fun didGameEnd(): Boolean = didGameEnd

    /**
     * Returns current level
     * */
    fun getLevel(): Level = level
}
