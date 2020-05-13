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

    fun endGame() {
        didGameEnd = true
    }

    fun resume() {
        didGameEnd = false
    }

    fun didGameEnd(): Boolean = didGameEnd

    fun getLevel(): Level = level
}
