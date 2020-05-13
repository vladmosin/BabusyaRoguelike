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
class Client(address: String, port: Int, val login: String) : Closeable {
    private val SHUTDOWN_TIMEOUT: Long = 5

    private val channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build()
    private val stub = GameGrpcKt.GameCoroutineStub(channel)
    private val id = receiveNextId()

    init {
        println("id = $id")
    }

    /**
     * Create room request
     * */
    fun createRoom(): Pair<Boolean, String> = runBlocking {
        val response = stub.createRoom(Empty.getDefaultInstance())
        return@runBlocking Pair(response.status, response.message)
    }

    /**
     * Get list of rooms id request
     * */
    fun getRooms(): List<Room>? = runBlocking {
        return@runBlocking stub.getRooms(Empty.getDefaultInstance()).toList()
    }

    /**
     * Join room request
     * */
    fun joinRoom(roomId: Int): Boolean = runBlocking {
        val room = Room.newBuilder().setId(roomId).build()
        val playerId = PlayerId.newBuilder().setId(id).build()
        val player = Player
            .newBuilder()
            .setPlayerId(playerId)
            .setLogin(login)
            .setRoom(room)
            .build()
        val response = stub.joinRoom(player)
        return@runBlocking response.status
    }

    /**
     * Leave room request
     * */
    fun leaveRoom(roomId: Int): Boolean = runBlocking {
        val room = Room.newBuilder().setId(roomId).build()
        val playerId = PlayerId.newBuilder().setId(id).build()
        val player = Player
            .newBuilder()
            .setPlayerId(playerId)
            .setLogin(login)
            .setRoom(room)
            .build()
        val response = stub.leaveRoom(player)
        return@runBlocking response.status
    }

    /**
     * Get state request
     * */
    fun getState(roomId: Int): Message = runBlocking {
        val room = Room.newBuilder().setId(roomId).build()
        val playerId = PlayerId.newBuilder().setId(id).build()
        val player = Player
            .newBuilder()
            .setPlayerId(playerId)
            .setLogin(login)
            .setRoom(room)
            .build()
        val state: State = stub.getState(player)
        return@runBlocking Message(state.level, state.ends, state.log)
    }

    /**
     * Send user action request
     * */
    fun sendInputData(data: String) = runBlocking {
        val inputData = inc.roguelike.babusya.network.gen.InputData.newBuilder()
            .setData(data)
            .setPlayerId(id)
            .build()
        stub.sendInputData(inputData)
    }

    /**
     * Receive id for new player request
     * */
    private fun receiveNextId(): Int = runBlocking {
        return@runBlocking stub.receiveNextId(Empty.getDefaultInstance()).id
    }

    /**
     * Closes client
     * */
    override fun close() {
        channel.shutdown().awaitTermination(SHUTDOWN_TIMEOUT, TimeUnit.SECONDS)
    }
}
