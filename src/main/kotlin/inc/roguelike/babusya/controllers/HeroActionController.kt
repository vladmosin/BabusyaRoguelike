package inc.roguelike.babusya.controllers

import InputListener
import inc.roguelike.babusya.FileSystem
import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.commands.AbstractCommand
import inc.roguelike.babusya.commands.MovingCommand
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.map.GameMap
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import java.io.File

/**
 * Implements players controller
 * Collects loot in current cell after move
 * */
class HeroActionController(gameMap: GameMap, val inputListener: InputListener): AbstractActionController(gameMap) {

    private val inputDataChannel = Channel<AbstractCommand>(capacity = Channel.CONFLATED)

    private fun saveMap() {
        FileSystem.saveToFile("Levels" + File.separator + "Saved", gameMap.serialize())
        log?.add("Game saved : successfully")
    }

    private fun receiveStep(): AbstractCommand {
        fun receive(command: AbstractCommand) {
            runBlocking { inputDataChannel.send(command) }
        }

        inputListener.addCommand { input -> receive(input) }

        var data: AbstractCommand? = null
        runBlocking { data = inputDataChannel.receive() }
        return data!!
    }

    /**
     * Receives command from inputListener and makes move to the target cell
     */
    override fun makeTurn(creature: Creature): Boolean {

        val command = receiveStep()
        val cell = gameMap.getCellByElement(creature)!!

        if (creature is Hero) {
            command.apply(gameMap, creature, log)
        }

        return if (command is MovingCommand) {
            val targetCell = command.apply(gameMap, creature, log, cell)
            makeMove(creature, targetCell)
            if (creature is Hero) {
                pickItem(creature)
            }
            true
        } else {
            false
        }
    }

    private fun pickItem(hero: Hero) {
        val cell = gameMap.getCellByElement(hero)!!
        val loot = cell.loot
        if (loot != null) {
            hero.inventory.addToInventory(loot)
            cell.loot = null
        }

    }

    override fun clone(): HeroActionController {
        return HeroActionController(gameMap, inputListener)
    }

    override fun serialize(): String {
        return collectToString(ControllerType.HeroController.name, listOf())
    }

    override fun setDeserializeInfo(args: List<String>): ActionController? {
        return this
    }
}
