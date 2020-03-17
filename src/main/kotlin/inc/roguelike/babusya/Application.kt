package inc.roguelike.babusya

import com.googlecode.lanterna.TerminalSize
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.swing.TerminalEmulatorAutoCloseTrigger
import inc.roguelike.babusya.UI.ConsoleRenderSystem
import inc.roguelike.babusya.inputListeners.ConsoleKeyboardListener
import inc.roguelike.babusya.levels.LevelCreator
import inc.roguelike.babusya.levels.LevelInfo
import inc.roguelike.babusya.levels.LevelsType
import java.util.*

/**
 * Initializes and starts game
 */
fun main() {
    val levelInfo = askUserForLevelsType()
    val terminal = DefaultTerminalFactory()
        .setInitialTerminalSize(TerminalSize(100, 30))
        .createTerminalEmulator()

    val renderSystem = ConsoleRenderSystem(terminal)
    val inputListener = ConsoleKeyboardListener(terminal)
    inputListener.start()

    Game(renderSystem, inputListener, levelInfo).launch()

    renderSystem.close()
}

fun askUserForLevelsType(): LevelInfo {
    val input = Scanner(System.`in`)
    println("Please select type of map")
    while(true) {
        println("To generate level write \"gen\" (without quotas)")
        println("To load level from file write \"load <level id>\" " +
                "(without quotas), instead of level id write number from 1 to ${Game.SAVED_LEVELS}")

        val userInput = input.nextLine()
        val inputParts = userInput.split(" ").filter { s -> s.isNotEmpty() }
        if (inputParts[0] == "gen") {
            return LevelInfo(1, LevelsType.GENERATED)
        }

        if (inputParts[0] == "load") {
            if (inputParts.size == 1) {
                println("Please, specify level id")
                continue
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