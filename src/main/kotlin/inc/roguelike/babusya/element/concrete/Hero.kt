package inc.roguelike.babusya.element.concrete

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.effects.ConfusionChanceEffect
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.effects.PunchEffect
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.abstracts.AbstractCreature
import inc.roguelike.babusya.element.concrete.memento.HeroMemento
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.loot.Inventory
import inc.roguelike.babusya.visitors.ElementVisitor


/**
 * Implements hero for player
 * */
class Hero(
    creatureCharacteristics: CreatureCharacteristics, actionController: ActionController?,
    id: String, elementStatus: ElementStatus, var experience: Int
) : AbstractCreature(creatureCharacteristics, actionController, id, elementStatus) {

    var inventory = Inventory(this)

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
        return HeroMemento.serialize(this);
    }

    override fun clone(): Hero {
        val newCharacteristics = characteristics.clone()
        return Hero(newCharacteristics, actionController?.clone(), id, elementStatus, experience)
}

    companion object {
        fun deserialize(controllerFactory: ControllerFactory, string: String): Hero? {
            return HeroMemento.deserialize(controllerFactory, string)
        }
    }
}
