package inc.roguelike.babusya.loot

import inc.roguelike.babusya.controllers.HeroActionController
import inc.roguelike.babusya.effects.HealEffect
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.mocks.MockInputListener
import inc.roguelike.babusya.mocks.MockMap
import junit.framework.Assert
import junit.framework.Assert.*
import org.junit.Test
import java.util.*

class InventoryTest {

    @Test
    fun useConsumableTest() {
        val hero = Hero(
            actionController = HeroActionController(MockMap(), MockInputListener(LinkedList())),
            creatureCharacteristics = CreatureCharacteristics(100, 200, 100),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h"
        )
        val potion = Potion("Heal potion", HealEffect(75))
        hero.inventory.addToInventory(potion)
        assertTrue(potion in hero.inventory.inPossesionOf)
        hero.inventory.selectItem(potion)
        assertEquals(hero.inventory.selected, potion)
        hero.inventory.useSelected()
        assertEquals(175, hero.characteristics.hitPoints)
        assertEquals(200, hero.characteristics.maxHitPoints)
        assertFalse(potion in hero.inventory.inPossesionOf)
    }

    @Test
    fun useEquipmentTest() {
        val hero = Hero(
            actionController = HeroActionController(MockMap(), MockInputListener(LinkedList())),
            creatureCharacteristics = CreatureCharacteristics(100, 200, 100),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h"
        )
        val hat = Equipment(EquipmentType.HAT, 42, 42)
        hero.inventory.addToInventory(hat)
        assertTrue(hat in hero.inventory.inPossesionOf)
        hero.inventory.selectItem(hat)
        assertEquals(hero.inventory.selected, hat)
        hero.inventory.useSelected()
        assertEquals(100, hero.characteristics.hitPoints)
        assertEquals(242, hero.characteristics.maxHitPoints)
        assertEquals(142, hero.characteristics.attack)
        assertTrue(hat in hero.inventory.inPossesionOf)
        assertEquals(hat, hero.inventory.equipped[EquipmentType.HAT])
    }
}
