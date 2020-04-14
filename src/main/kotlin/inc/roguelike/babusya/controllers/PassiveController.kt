package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.map.GameMap


class PassiveController(gameMap: GameMap): AbstractActionController(gameMap) {
    override fun makeTurn(creature: Creature) {}
}
