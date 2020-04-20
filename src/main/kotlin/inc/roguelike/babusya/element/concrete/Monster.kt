package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.effects.MonsterPunchEffect
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.abstracts.AbstractCreature
import inc.roguelike.babusya.element.interfaces.Creature
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clone(): Monster {
        val newCharacteristics = characteristics.clone()
        val monster = Monster(newCharacteristics, null, id, elementStatus)
        val newActionController = actionController?.clone(monster)

        monster.actionController = newActionController
        return monster
    }

    companion object {
        fun deserialize(string: String): Monster? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}
