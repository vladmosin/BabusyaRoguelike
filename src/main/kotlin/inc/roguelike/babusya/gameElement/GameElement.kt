package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.visitors.Visitor

/**
 * Basic class for all items on the game map
 * */
abstract class GameElement(val id: String, var elementStatus: ElementStatus) {
    /**
     * Calls appropriate visitor's method
     * */
    abstract fun <T> accept(visitor: Visitor<T>): T

    /**
     * Performs action on another game element. Different behaviour for different types of game elements.
     * */
    abstract fun act(gameElement: GameElement)

    /**
     * Decreases GameElement's hit points. If hit points become less or equal than 0, game element dies
     * */
    abstract fun bePunched(damage: Int)

    /**
     * Checks that current game element is not empty and ALIVE
     * */
    abstract fun isActive(): Boolean
}