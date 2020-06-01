package inc.roguelike.babusya.element.concrete.memento

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.loot.Inventory

class HeroMemento {
    companion object {
        private const val name = "Hero"

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

        fun serialize(hero: Hero): String {
            return collectToString(
                name, listOf(hero.characteristics.serialize(), hero.id, hero.elementStatus.name,
                hero.experience.toString(), hero.actionController!!.serialize(), hero.inventory.serialize()))
        }
    }
}