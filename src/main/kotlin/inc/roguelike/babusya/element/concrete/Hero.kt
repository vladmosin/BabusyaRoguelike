package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.controllers.ControllerType
import inc.roguelike.babusya.effects.ConfusionChanceEffect
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.effects.PunchEffect
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.abstracts.AbstractCreature
import inc.roguelike.babusya.visitors.ElementVisitor


/**
 * Implements hero for player
 * */
class Hero(
    creatureCharacteristics: CreatureCharacteristics, actionController: ActionController?,
    id: String, elementStatus: ElementStatus, var experience: Int
) : AbstractCreature(creatureCharacteristics, actionController, id, elementStatus) {

    override fun <T> accept(visitor: ElementVisitor<T>): T {
        return visitor.visitHero(this)
    }

    private fun getKickEffects(): List<Effect> {
        return listOf(PunchEffect(characteristics.attack), ConfusionChanceEffect(0.5, 10))
    }

    override fun attackEffects(): List<Effect> {
        return getKickEffects()
    }

    override fun defensiveEffects(): List<Effect> {
        return getKickEffects()
    }

    override fun isActive(): Boolean {
        return elementStatus == ElementStatus.ALIVE
    }

    override fun serialize(): String {
        return "$name#${characteristics.serialize()}#${id}#${elementStatus}#${experience}"
    }

    companion object {
        fun deserialize(controllerFactory: ControllerFactory, string: String): Hero? {
            val parts = string.split("#")
            return if (parts.size != 6) {
                null
            } else {
                val elementStatus =
                    ElementStatus.deserialize(parts[3])
                val creatureCharacteristics =
                    CreatureCharacteristics.deserialize(parts[1])

                if (elementStatus == null || creatureCharacteristics == null || parts[0] != name) {
                    null
                } else {
                    return try {
                        Hero(
                            creatureCharacteristics,
                            controllerFactory.createController(ControllerType.valueOf(parts[5])),
                            parts[2],
                            elementStatus,
                            parts[4].toInt()
                        )
                    } catch (e: NumberFormatException) {
                        null
                    }
                }
            }
        }

        private const val name = "h"
    }
}
