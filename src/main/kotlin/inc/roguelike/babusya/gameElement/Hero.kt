package inc.roguelike.babusya.gameElement

import InputListener
import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.controllers.HeroActionController
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.GameMap
import inc.roguelike.babusya.visitors.Visitor
import kotlin.math.max


/**
 * Implements hero for player
 * */
class Hero(creatureCharacteristics: CreatureCharacteristics, actionController: ActionController?,
           id: String, elementStatus: ElementStatus, var experience: Int):
    Creature(creatureCharacteristics, actionController, id, elementStatus) {

    override fun chooseMove(): Cell {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setController(cell: Cell, inputListener: InputListener, map: GameMap) {
        actionController = HeroActionController(cell, inputListener, map)
    }

    override fun <T> accept(visitor: Visitor<T>): T {
        return visitor.visitHero(this)
    }

    override fun act(gameElement: GameElement) {
        gameElement.bePunched(creatureCharacteristics.attack)
    }

    override fun bePunched(damage: Int) {
        creatureCharacteristics.hitPoints = max(0, creatureCharacteristics.hitPoints - damage)
        if (creatureCharacteristics.hitPoints == 0) {
            elementStatus = ElementStatus.DEAD
        }
    }

    override fun isActive(): Boolean {
        return elementStatus == ElementStatus.ALIVE
    }

    override fun serialize(): String {
        return "${name}#${creatureCharacteristics.serialize()}#${id}#${elementStatus}#${experience}"
    }

    companion object {
        fun deserialize(string: String): Hero? {
            val parts = string.split("#")
            return if (parts.size != 5) {
                null
            } else {
                val elementStatus = ElementStatus.deserialize(parts[3])
                val creatureCharacteristics = CreatureCharacteristics.deserialize(parts[1])

                if (elementStatus == null || creatureCharacteristics == null || parts[0] != name) {
                    null
                } else {
                    return try {
                        Hero(creatureCharacteristics, null, parts[2], elementStatus, parts[4].toInt())
                    } catch (e: NumberFormatException) {
                        null
                    }
                }
            }
        }

        private const val name = "h"
    }


}
