package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.GameMap

/**
 * Interface for different behaviour strategies
 * */
interface ActionController {
    /**
     * Moves creature due to its strategy
     * */
    fun makeTurn(creature: Creature)

    /**
     * Sets concrete log to output game events
     * */
    fun useLog(gameLog: GameLog)

    /**
     * Clones controller
     * */
    fun clone(): ActionController

    /**
     * Changes map which is controlled
     * */
    fun changeGameMap(gameMap: GameMap)

    /**
     * Serializes controller
     * */
    fun serialize(): String

    /**
     * Sets extra information after while deserializing
     * */
    fun setDeserializeInfo(args: List<String>): ActionController?
}
