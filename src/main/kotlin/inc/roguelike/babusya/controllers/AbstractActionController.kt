package inc.roguelike.babusya.controllers

import inc.roguelike.babusya.GameLog
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.effects.PunchEffect
import inc.roguelike.babusya.element.concrete.EmptyGameElement
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.GameMap

/**
 * Class with different methods implementation which are common for all creature action controllers
 * */
abstract class AbstractActionController(var gameMap: GameMap): ActionController {
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

        applyAllEffects(creature.attackEffects(), creature, toItem)
        if (toItem.isActive()) {
            applyAllEffects(toItem.defensiveEffects(), toItem, creature)
        }

        if (!toItem.isActive() && creature.isActive()) {
            fromCell.storedItem = EmptyGameElement()
            toCell.storedItem = creature
        }
    }

    private fun applyAllEffects(effects: List<Effect>, fromElement: GameElement, toElement: GameElement) {
        for (effect in effects) {
            if (effect.apply(toElement)) {
                log?.add(effect.getDescription(fromElement, toElement))
            }
        }
    }

    override fun changeGameMap(gameMap: GameMap) {
        this.gameMap = gameMap
    }
}
