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


    /**
     * Accept function for monster
     * */
    override fun <T> accept(visitor: ElementVisitor<T>): T {
        return visitor.visitMonster(this)
    }

    /**
     * Checks that monster is not dead
     * */
    override fun isActive(): Boolean {
        return elementStatus == ElementStatus.ALIVE
    }

    private fun getKickEffects(): List<Effect> {
        return listOf(MonsterPunchEffect(characteristics.attack))
    }

    /**
     * Effect when attacking
     * */
    override fun attackEffects(): List<Effect> {
        return getKickEffects()
    }

    /**
     * Effect when defending
     * */
    override fun defensiveEffects(): List<Effect> {
        return getKickEffects()
    }

    /**
     * Serializes monster
     * */
    override fun serialize(): String {
        return MonsterMemento.serialize(this)
    }

    /**
     * Clones monster
     * */
    override fun clone(): Monster {
        val newCharacteristics = characteristics.clone()
        return Monster(newCharacteristics, actionController?.clone(), id, elementStatus)
    }

    companion object {
        /**
         * Deserializes monster
         * */
        fun deserialize(controllerFactory: ControllerFactory, line: String): Monster? {
            return MonsterMemento.deserialize(controllerFactory, line)
        }
    }
}
