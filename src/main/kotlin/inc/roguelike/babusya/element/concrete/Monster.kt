package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.effects.MonsterPunchEffect
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.abstracts.AbstractCreature
import inc.roguelike.babusya.element.concrete.memento.MonsterMemento
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

    override fun attackEffects(): List<Effect> {
        return getKickEffects()
    }

    override fun defensiveEffects(): List<Effect> {
        return getKickEffects()
    }

    override fun serialize(): String {
        return MonsterMemento.serialize(this)
    }

    override fun clone(): Monster {
        val newCharacteristics = characteristics.clone()
        return Monster(newCharacteristics, actionController?.clone(), id, elementStatus)
    }

    companion object {
        fun deserialize(controllerFactory: ControllerFactory, line: String): Monster? {
            return MonsterMemento.deserialize(controllerFactory, line)
        }
    }
}
