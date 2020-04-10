package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.gameElement.Creature
import inc.roguelike.babusya.gameElement.EmptyGameElement
import inc.roguelike.babusya.gameElement.GameElement
import inc.roguelike.babusya.map.GameMap

abstract class AbstractActionController(val gameMap: GameMap): ActionController {
    private var log : GameLog? = null

    override fun useLog(gameLog: GameLog) {
        log = gameLog
    }

    /**
     * Moves item from first cell to the second cell if possible, otherwise makes other necessary operations.
     * */
    fun makeMove(creature: Creature, toCell: Cell) {
        val fromCell = gameMap.getCellByElement(creature)!!
        if (fromCell == toCell) return

        val toItem = toCell.storedItem

        applyEffects(creature.attackEffects(), creature, toItem)
        if (toItem.isActive()) {
            applyEffects(toItem.defensiveEffects(), toItem, creature)
        }

        if (!toItem.isActive() && creature.isActive()) {
            fromCell.storedItem = EmptyGameElement()
            toCell.storedItem = creature
        }
    }

    private fun applyEffects(effects: List<Effect>, fromElement: GameElement, toElement: GameElement) {
        for (effect in effects) {
            effect.apply(toElement)
            val description = effect.getDescription(fromElement, toElement)
            if (description != null) {
                log?.add(description)
            }
        }
    }
}
