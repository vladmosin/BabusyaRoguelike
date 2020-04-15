package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.gameElement.Creature

interface ActionController {
    fun makeTurn(creature: Creature)
    fun useLog(gameLog: GameLog)
}
