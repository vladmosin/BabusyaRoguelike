package inc.roguelike.babusya.network

import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.network.gen.*
import inc.roguelike.babusya.network.gen.Player

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.runBlocking
import java.io.Closeable
import java.util.concurrent.TimeUnit

/**
 * Sends and receives messages
 * */
class Client(address: String, port: Int) : Closeable {

    private val SHUTDOWN_TIMEOUT: Long = 5

    val channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build()
    val stub = GameGrpcKt.GameCoroutineStub(channel)

    fun getState(): Message = runBlocking {
        val state: State = stub.getState(Empty.getDefaultInstance())
        return@runBlocking Message(state.level, state.ends, state.log)
    }

//    fun createRoom(roomId: Int): Boolean = runBlocking {
//        val room = Room.newBuilder().setId(roomId).build()
//        val player = inc.roguelike.babusya.network.gen.Player
//            .newBuilder()
//            .setId()
//            .
//        return@runBlocking true
//    }

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

    override fun close() {
        channel.shutdown().awaitTermination(SHUTDOWN_TIMEOUT, TimeUnit.SECONDS)
    }
}
