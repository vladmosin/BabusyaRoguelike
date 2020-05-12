package inc.roguelike.babusya

import inc.roguelike.babusya.actionSystems.MultiplayerActionSystem
import inc.roguelike.babusya.engines.MultiPlayerEngine
import inc.roguelike.babusya.inputListeners.EmptyInputListener
import inc.roguelike.babusya.levels.LevelInfo
import inc.roguelike.babusya.levels.LevelsType
import inc.roguelike.babusya.network.Client
import java.util.logging.Level

class Server {
    val rooms = ArrayList<Room>()

    fun createRoom(roomId: Int, client: Client) {
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

    fun joinRoom(roomId: Int, client: Client): Boolean {
        for (room in rooms) {
            if (room.id == roomId) {
                room.addClient(client)
                return true
            }
        }

        return false
    }
}