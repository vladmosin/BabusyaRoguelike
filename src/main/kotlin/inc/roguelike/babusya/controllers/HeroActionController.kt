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
class HeroActionController(inputListener: InputListener,  gameMap: GameMap): AbstractActionController(gameMap) {

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
    override fun makeTurn(creature: Creature) {
        var data: InputData? = null
        runBlocking {
            data = inputDataChannel.receive()
        }

        val cell = gameMap.getCellByElement(creature)!!

        val targetCell = when (data!!) {
            InputData.RIGHT -> gameMap.getRighterCell(cell) ?: cell
            InputData.UP -> gameMap.getUpperCell(cell) ?: cell
            InputData.LEFT -> gameMap.getLefterCell(cell) ?: cell
            InputData.DOWN -> gameMap.getDownerCell(cell) ?: cell
        }

        makeMove(creature, targetCell)
    }
}
