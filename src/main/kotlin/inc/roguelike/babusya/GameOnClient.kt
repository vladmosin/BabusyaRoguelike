package inc.roguelike.babusya

import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.inputListeners.EmptyInputListener
import inc.roguelike.babusya.levels.Level
import inc.roguelike.babusya.network.Client
import inc.roguelike.babusya.network.Message
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameOnClient(private val renderSystem: RenderSystem, private val client: Client) {
    fun launch() {
        val inputListener = EmptyInputListener()
        while (true) {
//            val message = client.receiveMessage()
            val message = Message("", true, "")
            if (message.gameEnds) {
                break
            } else {
                val level = Level.deserialize(message.serializedLevel, inputListener)
                val gameLog = GameLog.deserialize(message.serializedGameLog)

                renderSystem.render(level!!, gameLog!!)
            }
        }
    }
}
