package inc.roguelike.babusya.controllers

import InputListener
import inc.roguelike.babusya.map.GameMap

class ControllerFactory(val gameMap: GameMap, inputListener: InputListener) {

    private val heroController = HeroActionController(gameMap, inputListener)
    private val passiveController = PassiveController(gameMap)

    fun createController(type: ControllerType) : ActionController {
        when (type) {
            ControllerType.HeroController -> return heroController
            ControllerType.PassiveController -> return passiveController
            ControllerType.AggressiveController -> return AggressiveController(gameMap, null)
            ControllerType.CowardController -> return CowardController(gameMap, null)
        }
    }
}