package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.controllers.ActionController
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.visitors.Visitor
import kotlin.math.max


/**
 * Implements hero for player
 * */
class Hero(creatureCharacteristics: CreatureCharacteristics, actionController: ActionController,
           id: String, elementStatus: ElementStatus, var experience: Int):
    Creature(creatureCharacteristics, actionController, id, elementStatus) {

    override fun chooseMove(): Cell {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        return
    }

    companion object {
        fun deserialize(string: String): Hero? {
            val parts = string.split("#")
            return if (parts.size != 3) {
                null
            } else {
                val elementStatus = ElementStatus.deserialize(parts[2])
                if (elementStatus == null || parts[0] != name) {
                    null
                } else {
                    Wall(parts[1], elementStatus)
                }
            }
        }

        private const val name = "h"
    }
}