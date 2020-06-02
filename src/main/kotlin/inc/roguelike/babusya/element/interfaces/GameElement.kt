package inc.roguelike.babusya.element.interfaces

import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.ElementStatusObserver
import inc.roguelike.babusya.element.abstracts.AbstractCreature
import inc.roguelike.babusya.element.abstracts.AbstractGameElement
import inc.roguelike.babusya.visitors.ElementVisitor

/**
 * Base interface for all elements in game
 * */
interface GameElement {
    val id: String
    var elementStatus: ElementStatus

    /**
     * Calls appropriate visitor's method
     * */
    fun <T> accept(visitor: ElementVisitor<T>): T

    fun attachStatusObserver(observer: ElementStatusObserver)

    /**
     * Checks that current game element is not empty and ALIVE
     * */
    fun isActive(): Boolean
    fun attackEffects() : List<Effect>
    fun defensiveEffects(): List<Effect>

    /**
     * Converts game element to string
     * Doesn't contain || as a subsequence
     * */
    fun serialize(): String

    /**
     * Clones game element
     * */
    fun clone(): GameElement

    companion object {
        fun deserialize(controllerFactory: ControllerFactory, string: String): GameElement? {
            return AbstractGameElement.deserialize(controllerFactory, string)
        }
    }
}
