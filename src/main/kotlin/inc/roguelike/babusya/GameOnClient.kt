package inc.roguelike.babusya

import inc.roguelike.babusya.UI.RenderSystem
import inc.roguelike.babusya.inputListeners.EmptyInputListener
import inc.roguelike.babusya.levels.Level
import inc.roguelike.babusya.network.Client

class GameOnClient(private val renderSystem: RenderSystem, private val client: Client) {
    fun launch() {
        val inputListener = EmptyInputListener()
        while (true) {
            val message = client.receiveMessage()
            if (message.gameEnds) {
                break
            } else {
                val level = Level.deserialize(message.serializedLevel, inputListener) ?: return
                val gameLog = GameLog.deserialize(message.serializedGameLog) ?: return

                renderSystem.render(level, gameLog)
            }
        }
    }
}