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
    if (args.size == 2 && args[0] == "server") {
        assert(args.size == 2)
        val port = args[1].toInt()
        val server = Server(port)
        server.start()
        server.blockUntilShutdown()
    } else if (args.size == 0) {
        launch<UserInteractionApp>()
    } else {
        TODO()
    }
}
