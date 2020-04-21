package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.GameMap

/**
 * Does not move
 * */
class PassiveController(gameMap: GameMap): AbstractActionController(gameMap) {
    override fun makeTurn(creature: Creature) {}

    override fun clone(gameElement: GameElement): PassiveController {
        return PassiveController(gameMap)
    }
}
