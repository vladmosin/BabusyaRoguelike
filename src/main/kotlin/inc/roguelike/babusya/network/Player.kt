package inc.roguelike.babusya.network

import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.inputListeners.InputData
import kotlinx.coroutines.sync.Mutex

class Player(var hero: Hero?, val id: Int) {
    var lastInputData: InputData? = null
    val mutex = Mutex(true)

    suspend fun receiveInputData(): InputData {
        println("RECEIVE INPUT DATA BEGIN")
        while (true) {
//            println("WTF $lastInputData")
            if (lastInputData == null) {
                mutex.lock()
            }
            if (lastInputData != null) {
                val result = lastInputData
                lastInputData = null
                println("RECEIVE INPUT DATA END result=$result")
                return result!!
            }
        }
    }

    fun setInputData(inputData: InputData) {
        lastInputData = inputData
        mutex.unlock()
    }
}
