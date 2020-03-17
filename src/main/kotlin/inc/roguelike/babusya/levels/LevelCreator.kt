package inc.roguelike.babusya.levels

import InputListener

class LevelCreator(inputListener: InputListener) {

    private val levelGenerator = LevelGenerator(inputListener)
    private val levelLoader = LevelLoader(inputListener)

    private val actionMap = mapOf(
        LevelsType.GENERATED to { id: Int -> levelGenerator.generateLevel(id)},
        LevelsType.LOADED to { id: Int -> levelLoader.loadLevel(id)}
    )

    fun createLevel(id: Int, levelsType: LevelsType): Level {
        return actionMap[levelsType]!!(id)
    }
}
