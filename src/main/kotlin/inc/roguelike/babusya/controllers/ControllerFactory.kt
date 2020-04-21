package inc.roguelike.babusya.controllers

import InputListener
import inc.roguelike.babusya.map.GameMap

class ControllerFactory(val gameMap: GameMap, inputListener: InputListener) {

    private val heroController = HeroActionController(gameMap, inputListener)
    private val passiveController = PassiveController(gameMap)
    private val randomController = RandomActionController(gameMap)

    fun createController(type: ControllerType) : ActionController {
        return when (type) {
            ControllerType.HeroController -> heroController
            ControllerType.PassiveController -> passiveController
            ControllerType.AggressiveController -> AggressiveController(gameMap, null)
            ControllerType.CowardController -> CowardController(gameMap, null)
            ControllerType.RandomController -> randomController
        }
    }
}
