package inc.roguelike.babusya.loot

import inc.roguelike.babusya.controllers.HeroActionController
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.inputListeners.InputData
import inc.roguelike.babusya.mocks.MockInputListener
import inc.roguelike.babusya.mocks.MockMap
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

class EquipmentTest {

    @Test
    fun addBuffs() {
        val hero = Hero(
            actionController = HeroActionController(MockMap(), MockInputListener(LinkedList())),
            creatureCharacteristics = CreatureCharacteristics(100, 200, 100),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h"
        )
        val hat = Equipment(EquipmentType.HAT, 50, 50)
        hat.addBuffs(hero)
        assertEquals(100, hero.characteristics.hitPoints)
        assertEquals(250, hero.characteristics.maxHitPoints)
        assertEquals(150, hero.characteristics.attack)
    }


    @Test
    fun removeBuffs() {
        val hero = Hero(
            actionController = HeroActionController(MockMap(), MockInputListener(LinkedList())),
            creatureCharacteristics = CreatureCharacteristics(100, 200, 100),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h"
        )
        val hat = Equipment(EquipmentType.HAT, 50, 50)
        hat.removeBuffs(hero)
        assertEquals(100, hero.characteristics.hitPoints)
        assertEquals(150, hero.characteristics.maxHitPoints)
        assertEquals(50, hero.characteristics.attack)
    }

    @Test
    fun removeBuffsHPLimit() {
        val hero = Hero(
            actionController = HeroActionController(MockMap(), MockInputListener(LinkedList())),
            creatureCharacteristics = CreatureCharacteristics(100, 200, 100),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h"
        )
        val hat = Equipment(EquipmentType.HAT, 120, 50)
        hat.removeBuffs(hero)
        assertEquals(80, hero.characteristics.hitPoints)
        assertEquals(80, hero.characteristics.maxHitPoints)
        assertEquals(50, hero.characteristics.attack)
    }
}
