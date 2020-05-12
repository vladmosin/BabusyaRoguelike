package inc.roguelike.babusya

import inc.roguelike.babusya.actionSystems.MultiplayerActionSystem
import inc.roguelike.babusya.engines.MultiPlayerEngine
import inc.roguelike.babusya.inputListeners.EmptyInputListener
import inc.roguelike.babusya.levels.LevelInfo
import inc.roguelike.babusya.levels.LevelsType
import inc.roguelike.babusya.network.Client
import inc.roguelike.babusya.network.gen.*
import io.grpc.ServerBuilder
import kotlinx.coroutines.flow.Flow


class Server constructor(port: Int) {
    private val server: Server

    init {
        val serverBuilder = ServerBuilder.forPort(port)
        server = serverBuilder.addService(GameService()).build()
    }

    val rooms = ArrayList<Room>()

    fun createRoom(roomId: Int, client: Int) {
        val inputListener = EmptyInputListener()
        val actionSystem = MultiplayerActionSystem()
        val engine = MultiPlayerEngine(actionSystem)
        val levelInfo = LevelInfo(1, LevelsType.GENERATED)
        val game = Game(inputListener, engine, levelInfo)

        val room = Room(game, roomId, client)
        rooms.add(room)
    }

    fun getRoom(roomId: Int): Room? {
        for (room in rooms) {
            if (room.id == roomId) {
                return room
            }
        }

        return null
    }

    fun joinRoom(roomId: Int, client: Int): Boolean {
        for (room in rooms) {
            if (room.id == roomId) {
                room.addClient(client)
                return true
            }
        }

        return false
    }

    fun start() {
        server.start()
        Runtime.getRuntime().addShutdownHook(
            Thread {
                println("*** shutting down gRPC server since JVM is shutting down")
                this@Server.stop()
                println("*** server shut down")
            }
        )
    }

    fun stop() {
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }

    inner class GameService : GameGrpcKt.GameCoroutineImplBase() {
        override suspend fun createRoom(request: Player): Response {
            val playerId = request.id
            val playerLogin = request.login
            val roomId = request.room.id
            TODO()
        }

        override fun getRooms(request: Empty): Flow<inc.roguelike.babusya.network.gen.Room> {
            TODO()
        }

        override suspend fun joinRoom(request: Player): Response {
            val playerId = request.id
            val playerLogin = request.login
            val roomId = request.room.id
            TODO()
        }

        override suspend fun getState(request: Player): State {
            val playerId = request.id
            val playerLogin = request.login
            val roomId = request.room.id
            TODO()
        }

        override suspend fun sendInputData(request: InputData): Empty {
            val playerId = request.playerId
            val data = request.data
            TODO()
        }
    }
}
