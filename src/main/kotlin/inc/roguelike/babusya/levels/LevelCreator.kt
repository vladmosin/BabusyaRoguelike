package inc.roguelike.babusya.levels

class LevelCreator {

    private val levelGenerator = LevelGenerator()
    private val levelLoader = LevelLoader()

    fun createLevel(id: Int): Level {
        return levelGenerator.generateLevel(id)
    }
}