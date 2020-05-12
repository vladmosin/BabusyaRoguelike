package inc.roguelike.babusya.network

import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.network.gen.GameGrpcKt

/**
 * Sends and receives messages
 * */
class Client(val address: String, val port: Int, val login: String, val id: Int) {

//    val channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build()
//    val stub = GameGrpcKt.GameCoroutineStub(channel)

    fun receiveMessage(): Message {
        TODO()
    }

    fun sendMessage(message: Message) {
//        val request = inc.roguelike.babusya.network.gen.Message.newBuilder()
//            .setLevel(message.serializedLevel)
//            .setEnds(message.gameEnds)
//            .setLog(message.serializedGameLog)
//            .build()

    }

    fun sendPlayerInput(inputData: InputData) {
        TODO()
    }

    fun receiveInputData(): InputData {
        TODO()
    }
}
