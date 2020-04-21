package inc.roguelike.babusya.UI

import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.GameState
import inc.roguelike.babusya.levels.Level

interface RenderSystem {
    fun render(level: Level, gameLog: GameLog)
}