package inc.roguelike.babusya

import inc.roguelike.babusya.UI.overture.UserInteractionApp
import inc.roguelike.babusya.levels.LevelCreator.Companion.SAVED_PATH
import inc.roguelike.babusya.levels.LevelInfo
import inc.roguelike.babusya.levels.LevelsType
import tornadofx.launch

/**
 * Initializes and starts game
 */
fun main(args: Array<String>) {
    if (args.size == 1 && args[0] == "server") {
        TODO()
    } else if (args.size == 0) {
        launch<UserInteractionApp>()
    } else {
        TODO()
    }
}