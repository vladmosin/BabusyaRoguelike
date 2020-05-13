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
 * Elements in game possess unique String identification and status
 * */
interface GameElement {
    val id: String
    var elementStatus: ElementStatus

    /**
     * Calls appropriate visitor's method
     * */
    fun <T> accept(visitor: ElementVisitor<T>): T

    /**
     * Attach observer on element status updates
     */
    fun attachStatusObserver(observer: ElementStatusObserver)

    /**
     * Checks that current game element is not empty and ALIVE
     * */
    fun isActive(): Boolean

    /**
     * Returns effects to be applied on another element on attack
     */
    fun attackEffects() : List<Effect>

    /**
     * Returns effects to be applied as response on another element attack
     */
    fun defensiveEffects(): List<Effect>

    /**
     * Converts game element to string
     * Doesn't contain || as a subsequence
     * */
    fun serialize(): String

    fun clone(): GameElement

    companion object {
        fun deserialize(controllerFactory: ControllerFactory, string: String): GameElement? {
            return AbstractGameElement.deserialize(controllerFactory, string)
        }
    }
}
