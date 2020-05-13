package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.effects.MonsterPunchEffect
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.abstracts.AbstractCreature
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.visitors.ElementVisitor


/**
 * Implements monster
 * */
class Monster(creatureCharacteristics: CreatureCharacteristics, actionController: ActionController?,
              id: String, elementStatus: ElementStatus
):
    AbstractCreature(creatureCharacteristics, actionController, id, elementStatus) {


    override fun <T> accept(visitor: ElementVisitor<T>): T {
        return visitor.visitMonster(this)
    }

    override fun isActive(): Boolean {
        return elementStatus == ElementStatus.ALIVE
    }

    private fun getKickEffects(): List<Effect> {
        return listOf(MonsterPunchEffect(characteristics.attack))
    }

    /**
     * On attack monster applies MonsterPunchEffect with damage based on monster attack characteristic
     */
    override fun attackEffects(): List<Effect> {
        return getKickEffects()
    }

    /**
     * On defense monster applies MonsterPunchEffect with damage based on monster attack characteristic
     */
    override fun defensiveEffects(): List<Effect> {
        return getKickEffects()
    }

    override fun serialize(): String {
        return collectToString(name, listOf(characteristics.serialize(),
            actionController!!.serialize(), id, elementStatus.name))
    }

    override fun clone(): Monster {
        val newCharacteristics = characteristics.clone()
        return Monster(newCharacteristics, actionController?.clone(), id, elementStatus)
    }

    companion object {
        private const val name = "Monster"

        fun deserialize(controllerFactory: ControllerFactory, line: String): Monster? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != this.name || args.size != 4) {
                null
            } else {
                val elementStatus =
                    ElementStatus.deserialize(args[3])
                val creatureCharacteristics =
                    CreatureCharacteristics.deserialize(args[0])
                val controller = controllerFactory.deserializeController(args[1])

                if (controller == null || elementStatus == null || creatureCharacteristics == null) {
                    null
                } else {
                    Monster(creatureCharacteristics, controller, args[2], elementStatus)
                }
            }
        }
    }
}
