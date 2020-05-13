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
import java.util.concurrent.atomic.AtomicInteger


class Server constructor(port: Int) {
    private val server: io.grpc.Server

    init {
        val serverBuilder = ServerBuilder.forPort(port)
        server = serverBuilder.addService(GameService()).build()
    }

    val rooms = ArrayList<Room>()

    fun createRoom(roomId: Int) {
        val inputListener = EmptyInputListener()
        val actionSystem = MultiplayerActionSystem()
        val engine = MultiPlayerEngine(actionSystem)
        val levelInfo = LevelInfo(1, LevelsType.MULTIPLE_HEROES)
        val game = Game(inputListener, engine, levelInfo)

        val room = Room(game, roomId, engine.actionSystem.playersHolder)

        println("createRoom ${room.playersHolder.newClients}")
        
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
        val room = getRoom(roomId)
        return if (room != null) {
            room.addClient(client)
            true
        } else {
            false
        }
    }

    fun start() {
        server.start()
        println("Server is working")
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
        private var currentId = AtomicInteger(0)

        override suspend fun createRoom(request: Empty): Response {
            val roomId = currentId.addAndGet(1)

            for (room in rooms) {
                if (room.id == roomId) {
                    return Response.newBuilder().setMessage("Room already exists").setStatus(false).build()
                }
            }

            println("create room room_id=${roomId}")

            createRoom(roomId)
            val result =  Response.newBuilder().setMessage(roomId.toString()).setStatus(true).build()
            println("after creating num of rooms = ${rooms.size}")

            return result
        }

        override fun getRooms(request: Empty): Flow<inc.roguelike.babusya.network.gen.Room> = flow {
            rooms.map { room ->
                inc.roguelike.babusya.network.gen.Room.newBuilder().setId(room.id).build()
            }.forEach { room ->
                emit(room)
            }
        }

        override suspend fun joinRoom(request: Player): Response {
            val playerId = request.playerId.id
            val playerLogin = request.login
            val roomId = request.room.id

            val status = joinRoom(roomId, playerId)

            if (status)
                println("Join room playerId=$playerId, playerLogin=$playerLogin, roomId=$roomId")
            else
                println("NOT Join room playerId=$playerId, playerLogin=$playerLogin, roomId=$roomId")

            return Response.newBuilder().setMessage("").setStatus(status).build()
        }

        override suspend fun getState(request: Player): State {

            val playerId = request.playerId.id
            val playerLogin = request.login
            val roomId = request.room.id

//            println("RECEIVE REQUEST: GET STATE get State playerId=$playerId, playerLogin=$playerLogin, roomId=$roomId, ")

            val room = getRoom(roomId)!!
            val log = room.game.gameState.gameLog
            val level = room.game.gameState.getLevel()
            val ends = room.game.gameState.didGameEnd()

//            println("ends=$ends")
//            println("level=${level.serialize()}")
//            println("log=${log.serialize()}")

            return State.newBuilder()
                .setLog(log.serialize())
                .setLevel(level.serialize())
                .setEnds(ends)
                .build()
        }

        override suspend fun sendInputData(request: InputData): Empty {
            val playerId = request.playerId
            val data = request.data

            println("Input from player with id = ${playerId}, data = $data")

            for (room in rooms) {
                val player = room.findPlayer(playerId)
                if (player != null) {
                    player.setInputData(inc.roguelike.babusya.inputListeners.InputData.valueOf(data))
                    println("room = ${room.id}, player = ${player.id} inputData = ${player.lastInputData}")
                }
            }

            return Empty.getDefaultInstance()
        }

        override suspend fun receiveNextId(request: Empty): PlayerId {
            return PlayerId.newBuilder().setId(currentId.addAndGet(1)).build()
        }

        override suspend fun leaveRoom(request: Player): Response {
            val playerId = request.playerId.id
            val playerLogin = request.login
            val roomId = request.room.id

            val room = getRoom(roomId)

            if (room == null) {
                return Response.newBuilder().setStatus(false).setMessage("Room (id=$roomId) not found").build()
            }

            val player = room.findPlayer(playerId)

            if (player == null) {
                return Response.newBuilder()
                    .setStatus(false)
                    .setMessage("Player (id=$playerId, login=$playerLogin) room (id=$roomId) not found")
                    .build()
            }

            room.removePlayer(player)

            return Response.newBuilder().setStatus(true).setMessage("OK").build()
        }
    }
}
