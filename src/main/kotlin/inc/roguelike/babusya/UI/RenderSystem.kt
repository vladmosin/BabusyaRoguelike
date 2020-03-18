package inc.roguelike.babusya.UI

import inc.roguelike.babusya.levels.Level

interface RenderSystem {

    fun render(level: Level)
}