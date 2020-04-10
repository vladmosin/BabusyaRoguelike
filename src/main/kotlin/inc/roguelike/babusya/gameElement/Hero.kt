package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.controllers.ControllerType
import inc.roguelike.babusya.visitors.Visitor


/**
 * Implements hero for player
 * */
class Hero(
    creatureCharacteristics: CreatureCharacteristics, actionController: ActionController?,
    id: String, elementStatus: ElementStatus, var experience: Int
) :
    Creature(creatureCharacteristics, actionController, id, elementStatus) {

    override fun <T> accept(visitor: Visitor<T>): T {
        return visitor.visitHero(this)
    }


    override fun isActive(): Boolean {
        return elementStatus == ElementStatus.ALIVE
    }

    override fun serialize(): String {
        return "${name}#${characteristics.serialize()}#${id}#${elementStatus}#${experience}"
    }

    companion object {
        fun deserialize(controllerFactory: ControllerFactory, string: String): Hero? {
            val parts = string.split("#")
            return if (parts.size != 6) {
                null
            } else {
                val elementStatus = ElementStatus.deserialize(parts[3])
                val creatureCharacteristics = CreatureCharacteristics.deserialize(parts[1])

                if (elementStatus == null || creatureCharacteristics == null || parts[0] != name) {
                    null
                } else {
                    return try {
                        Hero(creatureCharacteristics, controllerFactory.createController(ControllerType.valueOf(parts[5])), parts[2], elementStatus, parts[4].toInt())
                    } catch (e: NumberFormatException) {
                        null
                    }
                }
            }
        }

        private const val name = "h"
    }
}
