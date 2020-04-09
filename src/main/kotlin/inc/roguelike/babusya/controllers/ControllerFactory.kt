package inc.roguelike.babusya.controllers

import InputListener
import inc.roguelike.babusya.map.GameMap

class ControllerFactory(val gameMap: GameMap, val inputListener: InputListener) {

    val heroController = HeroActionController(gameMap, inputListener)

    fun createController(type: ControllerType) : ActionController {
        when (type) {
            ControllerType.HeroController -> return heroController
        }
    }
}