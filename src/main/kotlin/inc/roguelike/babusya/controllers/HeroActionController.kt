package inc.roguelike.babusya.controllers

import InputListener
import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.map.Cell

/**
 * Implements players controller
 * */
class HeroActionController(private val inputListener: InputListener): ActionController {

    override fun makeTurn() {
        var inputData = inputListener.readInput()

    }
}