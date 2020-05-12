package inc.roguelike.babusya.controllers

import InputListener
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.map.GameMap

/**
 * Creates different controllers with predefined map and input listener
 * */
class ControllerFactory(private val gameMap: GameMap, inputListener: InputListener) {

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

    fun deserializeController(line: String): ActionController? {
        val name = getName(line)
        val args = getArguments(line)

        if (name == null || args == null) {
            return null
        }

        return if (name in ControllerType.values().map {type-> type.name}) {
            when (ControllerType.valueOf(name)) {
                ControllerType.HeroController ->
                    heroController.setDeserializeInfo(args)
                ControllerType.PassiveController ->
                    passiveController.setDeserializeInfo(args)
                ControllerType.AggressiveController ->
                    AggressiveController(gameMap, null).setDeserializeInfo(args)
                ControllerType.CowardController ->
                    CowardController(gameMap, null).setDeserializeInfo(args)
                ControllerType.RandomController -> randomController.setDeserializeInfo(args)
            }
        } else {
            null
        }
    }
}
