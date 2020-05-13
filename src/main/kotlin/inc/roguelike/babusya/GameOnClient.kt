package inc.roguelike.babusya

import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.inputListeners.EmptyInputListener
import inc.roguelike.babusya.levels.Level
import inc.roguelike.babusya.network.Client
import inc.roguelike.babusya.network.Message
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

class GameOnClient(private val renderSystem: RenderSystem, private val client: Client, private val roomId: Int) {
    fun launch() = runBlocking {
        val inputListener = EmptyInputListener()

        client.getStatesFlow(roomId).collect { state ->
            val message = Message(state.level, state.ends, state.log)

            if (message.gameEnds) {
                return@collect
            }

            val level = Level.deserialize(message.serializedLevel, inputListener)
            val gameLog = GameLog.deserialize(message.serializedGameLog)

//            println("CLIENT receive: level=${message.serializedLevel}, log=${message.serializedGameLog}, ends=${message.gameEnds}")
//            println("Level = ${level}")
//            println("gameLog = ${gameLog}")

            renderSystem.render(level!!, gameLog!!)

//            println("rendered updated level")
        }
    }
}
