package inc.roguelike.babusya

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import inc.roguelike.babusya.UI.ConsoleRenderSystem
import inc.roguelike.babusya.inputListeners.ConsoleKeyboardListener
import inc.roguelike.babusya.levels.LevelCreator.Companion.SAVED_PATH
import inc.roguelike.babusya.levels.LevelInfo
import inc.roguelike.babusya.levels.LevelsType

/**
 * Initializes and starts game
 */
fun main() {
    val levelInfo = askUserForLevelsType()
    val terminal = DefaultTerminalFactory()
        .setInitialTerminalSize(TerminalSize(130, 30))
        .createTerminalEmulator()

    val renderSystem = ConsoleRenderSystem(terminal)
    val inputListener = ConsoleKeyboardListener(terminal)
    inputListener.start()

    Game(renderSystem, inputListener, levelInfo).launch()

    renderSystem.close()
}

/**
 * Gets all information about game start from user
 * */
fun askUserForLevelsType(): LevelInfo {
    println("Please select type of map")
    val saveExists = haveSavedGame()
    while (true) {
        println("To generate level write \"gen\" (without quotas)")
        println("To load level from file write \"load <level id>\" " +
                "(without quotas), instead of level id write number from 1 to ${Game.SAVED_LEVELS}")
        if (saveExists) {
            println("To load recently saved level write \"load saved\" (without quotas)")
        }

        var userInput = readLine()
        while (userInput == null) {
            userInput = readLine()
        }
        val inputParts = userInput.split(" ").filter { s -> s.isNotEmpty() }
        if (inputParts.isEmpty()) continue

        if (inputParts[0] == "gen") {
            return LevelInfo(1, LevelsType.GENERATED)
        }

        if (inputParts[0] == "load") {
            if (inputParts.size == 1) {
                println("Please, specify level id")
                continue
            }

            if (inputParts[1] == "saved" && saveExists) {
                return LevelInfo(1, LevelsType.SAVED)
            }

            try {
                val id = inputParts[1].toInt()
                if (id >= 1 && id <= Game.SAVED_LEVELS) {
                    return LevelInfo(id, LevelsType.LOADED)
                }
            } catch (e: NumberFormatException) {
                println("Level id should be a number from 1 to ${Game.SAVED_LEVELS}")
            }

            continue
        }

        println("Please, read instructions once again")
    }
}

/**
 * Checks that there is a saved game
 * */
fun haveSavedGame() = FileSystem.fileExists(SAVED_PATH)
