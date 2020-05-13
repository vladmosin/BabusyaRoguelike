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

    /**
     * On attack hero applies
     * MonsterPunchEffect with damage based on hero's attack characteristic
     * and Confusion effect
     */
    override fun attackEffects(): List<Effect> {
        return getKickEffects()
    }

    /**
     * On defense hero applies
     * MonsterPunchEffect with damage based on hero's attack characteristic
     * and Confusion effect
     */
    override fun defensiveEffects(): List<Effect> {
        return getKickEffects()
    }

    override fun isActive(): Boolean {
        return elementStatus == ElementStatus.ALIVE
    }

    override fun serialize(): String {
        return collectToString(name, listOf(characteristics.serialize(), id, elementStatus.name,
            experience.toString(), actionController!!.serialize(), inventory.serialize()))
    }

    override fun clone(): Hero {
        val newCharacteristics = characteristics.clone()
        return Hero(newCharacteristics, actionController?.clone(), id, elementStatus, experience)
    }

    companion object {
        private const val name = "Hero"

        /**
         * Creates hero with random characteristics
         * */
        fun create(actionController: ActionController): Hero {
            TODO()
        }

        fun deserialize(controllerFactory: ControllerFactory, string: String): Hero? {
            val name = getName(string)
            val args = getArguments(string)

            if (name == null || args == null || name != this.name || args.size != 6) {
                return null
            }

            val elementStatus =
                ElementStatus.deserialize(args[2])
            val creatureCharacteristics =
                CreatureCharacteristics.deserialize(args[0])
            val controller = controllerFactory.deserializeController(args[4])

            return if (elementStatus == null || creatureCharacteristics == null || controller == null) {
                null
            } else {
                try {
                    val hero = Hero(creatureCharacteristics, controller,
                        args[1], elementStatus, args[3].toInt()
                    )
                    val inventory = Inventory.deserialize(args[5], hero) ?: return null
                    hero.inventory = inventory

                    hero
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }
    }
}
