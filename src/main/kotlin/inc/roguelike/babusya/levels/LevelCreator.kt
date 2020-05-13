package inc.roguelike.babusya.levels

import java.io.File

import InputListener

/**
 * Creates different levels
 * */
class LevelCreator(inputListener: InputListener) {

    private val levelGenerator = LevelGenerator(inputListener)
    private val levelLoader = LevelLoader(inputListener)

    private val actionMap = mapOf(
        LevelsType.GENERATED to { id: Int -> levelGenerator.generateLevel(id)},
        LevelsType.LOADED to { id: Int -> levelLoader.loadLevel("Levels" + File.separator + "Level${id}", "Loaded level ${id}")},
        LevelsType.SAVED to {id: Int -> levelLoader.loadLevel(SAVED_PATH, "SavedLevel")},
        LevelsType.MULTIPLE_HEROES to {id: Int -> levelGenerator.generateWithoutHero(id)}
    )

    fun createLevel(id: Int, levelsType: LevelsType): Level {
        return actionMap[levelsType]!!(id)
    }

    companion object {
        val SAVED_PATH = "Levels" + File.separator + "Saved"
    }
}
