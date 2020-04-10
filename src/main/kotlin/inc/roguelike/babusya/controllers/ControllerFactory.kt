package inc.roguelike.babusya.controllers

import InputListener
import inc.roguelike.babusya.map.GameMap

class ControllerFactory(val gameMap: GameMap, val inputListener: InputListener) {

    private val heroController = HeroActionController(gameMap, inputListener)
    private val passiveController = PassiveController(gameMap)

    // TODO fix
    private val aggressiveController = AggressiveController(gameMap)
    private val cowardController = CowardController(gameMap)

    fun createController(type: ControllerType) : ActionController {
        when (type) {
            ControllerType.HeroController -> return heroController
            ControllerType.PassiveController -> return passiveController
            ControllerType.AggressiveController -> return aggressiveController
            ControllerType.CowardController -> return cowardController
        }
    }
}