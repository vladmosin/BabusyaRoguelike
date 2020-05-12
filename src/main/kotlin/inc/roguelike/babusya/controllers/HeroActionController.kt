package inc.roguelike.babusya.controllers

import InputListener
import inc.roguelike.babusya.FileSystem
import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.map.GameMap
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking
import java.io.File

/**
 * Implements players controller
 * Collects loot in current cell after move
 * */
class HeroActionController(gameMap: GameMap, var inputListener: InputListener): AbstractActionController(gameMap) {

    private val inputDataChannel = Channel<InputData>(capacity = Channel.CONFLATED)
    private val heroCommands = listOf(
        InputData.RIGHT,
        InputData.UP,
        InputData.LEFT,
        InputData.DOWN,
        InputData.INVENTORY_TOGGLE,
        InputData.INVENTORY_DOWN,
        InputData.INVENTORY_UP,
        InputData.SAVE
    )

    private fun saveMap() {
        FileSystem.saveToFile("Levels" + File.separator + "Saved", gameMap.serialize())
        log?.add("Game saved : successfully")
    }

    private fun receiveStep(creature: Creature): InputData {
        fun receive(input: InputData) {
            if (input in heroCommands)
                runBlocking { inputDataChannel.send(input) }
            else {
                inputListener.addCommand { inputData -> receive(inputData) }
            }
        }

        inputListener.addCommand { input -> receive(input) }

        var data: InputData? = null
        runBlocking { data = inputDataChannel.receive() }
        return data!!
    }

    /**
     * Receives command from inputListener
     * If command specifies direction, makes move to the target cell
     * If command is one of the inventory commands, executes it without consuming turn
     */
    override fun makeTurn(creature: Creature): Boolean {

        val data = receiveStep(creature)
        val cell = gameMap.getCellByElement(creature)!!

        if (creature is Hero) {
            when (data) {
                InputData.INVENTORY_TOGGLE -> creature.inventory.useSelected()
                InputData.INVENTORY_UP -> creature.inventory.selectPreviousLoot()
                InputData.INVENTORY_DOWN -> creature.inventory.selectNextLoot()
                InputData.SAVE -> saveMap()
            }
        }
        val targetCell = when (data) {
            InputData.RIGHT -> gameMap.getRighterCell(cell) ?: cell
            InputData.UP -> gameMap.getUpperCell(cell) ?: cell
            InputData.LEFT -> gameMap.getLefterCell(cell) ?: cell
            InputData.DOWN -> gameMap.getDownerCell(cell) ?: cell
            else -> null
        } ?: return false

        makeMove(creature, targetCell)

        if (creature is Hero) {
            pickItem(creature)
        }
        return true
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
