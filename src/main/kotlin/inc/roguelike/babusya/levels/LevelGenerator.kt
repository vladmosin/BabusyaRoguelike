package inc.roguelike.babusya.levels

import InputListener
import inc.roguelike.babusya.effects.HealEffect
import inc.roguelike.babusya.loot.Equipment
import inc.roguelike.babusya.loot.EquipmentType
import inc.roguelike.babusya.loot.Potion
import inc.roguelike.babusya.map.rectangularMap.RectangularMapBuilder

/**
 * Creates random level
 * */
class LevelGenerator(val inputListener: InputListener) {

    /**
     * Generates level
     * */
    fun generateLevel(id: Int): Level {
        val map = RectangularMapBuilder(10, 20)
            .addRandomWalls(0.95)
            .addBasicMonsters()
            .addLootRandomly(Equipment(EquipmentType.HAT, 100, 100))
            .addLootRandomly(Potion("Heal Potion", HealEffect(10000)))
            .buildMap(inputListener)
        return Level(map, "Level: $id", id)
    }
}
