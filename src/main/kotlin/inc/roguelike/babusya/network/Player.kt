package inc.roguelike.babusya.network

import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.inputListeners.InputData

class Player(var hero: Hero?, val id: Int) {
    var lastInputData: InputData? = null
    fun receiveInputData(): InputData {
        while (true) {
            if (lastInputData != null) {
                val result = lastInputData
                lastInputData = null
                return result!!
            }
        }
    }

    fun setInputData(inputData: InputData) {
        lastInputData = inputData
    }
}