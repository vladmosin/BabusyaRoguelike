package inc.roguelike.babusya.levels

import InputListener

class LevelCreator(val inputListener: InputListener) {

    private val levelGenerator = LevelGenerator(inputListener)
    private val levelLoader = LevelLoader()

    fun createLevel(id: Int): Level {
        return levelGenerator.generateLevel(id)
    }
}