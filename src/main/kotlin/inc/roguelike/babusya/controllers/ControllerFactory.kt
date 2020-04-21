package inc.roguelike.babusya.controllers

import InputListener
import inc.roguelike.babusya.map.GameMap

/**
 * Creates different controllers with predefined map and input listener
 * */
class ControllerFactory(val gameMap: GameMap, inputListener: InputListener) {

    private val heroController = HeroActionController(gameMap, inputListener)
    private val passiveController = PassiveController(gameMap)
    private val randomController = RandomActionController(gameMap)

    /**
     * Creates controller by its type
     * */
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
