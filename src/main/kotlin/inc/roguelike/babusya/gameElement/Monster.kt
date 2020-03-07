package inc.roguelike.babusya.gameElement

import inc.roguelike.babusya.Cell
import inc.roguelike.babusya.Visitor
import inc.roguelike.babusya.controllers.ActionController
import kotlin.math.max


/**
 * Implements monster
 * */
class Monster(creatureCharacteristics: CreatureCharacteristics, actionController: ActionController,
              id: String, elementStatus: ElementStatus):
    Creature(creatureCharacteristics, actionController, id, elementStatus) {

    override fun chooseMove(): Cell {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <T> accept(visitor: Visitor<T>): T {
        return visitor.visitMonster(this)
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
}