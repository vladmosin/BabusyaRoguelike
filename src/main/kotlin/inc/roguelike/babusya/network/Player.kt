package inc.roguelike.babusya.network

import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.inputListeners.InputData
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.withTimeout

class Player(var hero: Hero?, val id: Int) {
    private val inputDataChannel = Channel<InputData>(capacity = Channel.CONFLATED)

    suspend fun receiveInputData(): InputData {
        println("RECEIVE INPUT DATA BEGIN")
        try {
            withTimeout(timeMillis = 10000) {
                return@withTimeout inputDataChannel.receive()
            }
        } finally {
            TODO()
        }
    }

    suspend fun setInputData(inputData: InputData) {
        inputDataChannel.send(inputData)
    }
}
