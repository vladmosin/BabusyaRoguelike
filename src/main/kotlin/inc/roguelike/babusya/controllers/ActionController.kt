package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.GameMap

interface ActionController {
    fun makeTurn(creature: Creature)

    fun useLog(gameLog: GameLog)

    fun clone(gameElement: GameElement): ActionController

    fun changeGameMap(gameMap: GameMap)
}
