package inc.roguelike.babusya.controllers

import InputListener
import inc.roguelike.babusya.gameElement.Creature
import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.GameMap
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking

/**
 * Implements players controller
 * */
class HeroActionController(gameMap: GameMap, val inputListener: InputListener): AbstractActionController(gameMap) {

    private val inputDataChannel = Channel<InputData>(capacity = Channel.CONFLATED)
    private val stepCommands = listOf(InputData.RIGHT, InputData.UP, InputData.LEFT, InputData.DOWN)


    private fun receiveStep(): InputData {
        fun receive(input: InputData) {
            if (input in stepCommands)
                runBlocking { inputDataChannel.send(input) }
            else
                inputListener.addCommand {inputData -> receive(inputData)}
        }

        inputListener.addCommand { input -> receive(input) }

        var data: InputData? = null
        runBlocking { data = inputDataChannel.receive() }
        return data!!
    }

    /**
     * Receives command from inputListener and makes move to the target cell
     */
    override fun makeTurn(creature: Creature) {

        val data = receiveStep()
        val cell = gameMap.getCellByElement(creature)!!

        val targetCell = when (data) {
            InputData.RIGHT -> gameMap.getRighterCell(cell) ?: cell
            InputData.UP -> gameMap.getUpperCell(cell) ?: cell
            InputData.LEFT -> gameMap.getLefterCell(cell) ?: cell
            InputData.DOWN -> gameMap.getDownerCell(cell) ?: cell
        }

        makeMove(creature, targetCell)
    }
}
