package inc.roguelike.babusya.controllers

import InputListener
import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.GameMap
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.runBlocking

/**
 * Implements players controller
 * */
class HeroActionController(cell: Cell, inputListener: InputListener, val map: GameMap): ActionController(cell) {

    val inputDataChannel = Channel<InputData>(capacity = Channel.CONFLATED)

    init {
        inputListener.addCommand {
                input ->
            runBlocking {
                inputDataChannel.send(input)
            }
        }
    }

    /**
     * Receives command from inputListener and makes move to the target cell
     */
    override fun makeTurn() {
        var data: InputData? = null
        runBlocking {
            data = inputDataChannel.receive()
        }

        val targetCell = when (data!!) {
            InputData.RIGHT -> map.getRighterCell(cell) ?: cell
            InputData.UP -> map.getUpperCell(cell) ?: cell
            InputData.LEFT -> map.getLefterCell(cell) ?: cell
            InputData.DOWN -> map.getDownerCell(cell) ?: cell
        }

        makeMove(targetCell)
    }
}