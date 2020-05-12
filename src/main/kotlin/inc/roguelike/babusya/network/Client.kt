package inc.roguelike.babusya.network

import inc.roguelike.babusya.network.gen.*
import inc.roguelike.babusya.network.gen.Player
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import java.io.Closeable
import java.util.concurrent.TimeUnit


/**
 * Sends and receives messages
 * */
class Client(address: String, port: Int, val login: String, val id: Int) : Closeable {

    private val SHUTDOWN_TIMEOUT: Long = 5

    val channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build()
    val stub = GameGrpcKt.GameCoroutineStub(channel)

    fun createRoom(roomId: Int): Boolean = runBlocking {
        val room = Room.newBuilder().setId(roomId).build()
        val player = Player
            .newBuilder()
            .setId(id)
            .setLogin(login)
            .setRoom(room)
            .build()
        val response = stub.createRoom(player)
        return@runBlocking response.status
    }

    fun getRooms(): List<Room>? = runBlocking {
        return@runBlocking stub.getRooms(Empty.getDefaultInstance()).toList()
    }

    fun joinRoom(roomId: Int): Boolean = runBlocking {
        val room = Room.newBuilder().setId(roomId).build()
        val player = Player
            .newBuilder()
            .setId(id)
            .setLogin(login)
            .setRoom(room)
            .build()
        val response = stub.joinRoom(player)
        return@runBlocking response.status
    }

    fun getState(): Message = runBlocking {
        val state: State = stub.getState(Empty.getDefaultInstance())
        return@runBlocking Message(state.level, state.ends, state.log)
    }

    fun sendInputData(data: String) = runBlocking {
        val inputData = inc.roguelike.babusya.network.gen.InputData.newBuilder()
            .setData(data)
            .setPlayerId(id)
            .build()
        stub.sendInputData(inputData)
    }

    override fun close() {
        channel.shutdown().awaitTermination(SHUTDOWN_TIMEOUT, TimeUnit.SECONDS)
    }
}
