package inc.roguelike.babusya

import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.inputListeners.EmptyInputListener
import inc.roguelike.babusya.levels.Level
import inc.roguelike.babusya.network.Client
import inc.roguelike.babusya.network.Message
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameOnClient(private val renderSystem: RenderSystem, private val client: Client, private val roomId: Int) {
    fun launch() {
        val inputListener = EmptyInputListener()
        while (true) {
//            println("LISTEN STATE")
            val message = client.getState(roomId) // TODO: remove room id plug
            if (message.gameEnds) {
                break
            } else {
                val level = Level.deserialize(message.serializedLevel, inputListener)
                val gameLog = GameLog.deserialize(message.serializedGameLog)

//                println("CLIENT receive: level=${message.serializedLevel}, log=${message.serializedGameLog}, ends=${message.gameEnds}")
//                println("Level = ${level}")
//                println("gameLog = ${gameLog}")

                renderSystem.render(level!!, gameLog!!)

//                println("rendered updated level")
            }
        }
    }
}
