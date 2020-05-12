package inc.roguelike.babusya

import inc.roguelike.babusya.actionSystems.MultiplayerActionSystem
import inc.roguelike.babusya.engines.MultiPlayerEngine
import inc.roguelike.babusya.inputListeners.EmptyInputListener
import inc.roguelike.babusya.levels.LevelInfo
import inc.roguelike.babusya.levels.LevelsType
import inc.roguelike.babusya.network.gen.*
import io.grpc.ServerBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class Server constructor(port: Int) {
    private val server: io.grpc.Server

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
        private var currentId = 0

        override suspend fun createRoom(request: Player): Response {
            val playerId = request.playerId.id
            val playerLogin = request.login
            val roomId = request.room.id

            for (room in rooms) {
                if (room.id == roomId) {
                    return Response.newBuilder().setMessage("Room already exists").build()
                }
            }

            createRoom(roomId, playerId)
            return Response.newBuilder().setMessage("").build()
        }

        override fun getRooms(request: Empty): Flow<inc.roguelike.babusya.network.gen.Room> {
            return flow {
                rooms.map { room ->
                    inc.roguelike.babusya.network.gen.Room.newBuilder().setId(room.id).build()
                }
            }
        }

        override suspend fun joinRoom(request: Player): Response {
            val playerId = request.playerId.id
            val playerLogin = request.login
            val roomId = request.room.id

            for (room in rooms) {
                if (room.id == roomId) {
                    joinRoom(roomId, playerId)
                }
            }

            return Response.newBuilder().setMessage("").build()
        }

        override suspend fun getState(request: Player): State {
            val playerId = request.playerId.id
            val playerLogin = request.login
            val roomId = request.room.id

            val room = getRoom(roomId)!!
            val log = room.game.gameState.gameLog
            val level = room.game.gameState.getLevel()
            val ends = room.game.gameState.didGameEnd()

            return State.newBuilder()
                .setLog(log.serialize())
                .setLevel(level.serialize())
                .setEnds(ends)
                .build()
        }

        override suspend fun sendInputData(request: InputData): Empty {
            val playerId = request.playerId
            val data = request.data

            for (room in rooms) {
                val player = room.findPlayer(playerId)
                if (player != null) {
                    player.lastInputData = inc.roguelike.babusya.inputListeners.InputData.valueOf(data)
                }
            }

            return Empty.getDefaultInstance()
        }

        override suspend fun receiveNextId(request: Empty): PlayerId {
            val response = PlayerId.newBuilder().setId(currentId).build()
            currentId++
            return response
        }
    }
}
