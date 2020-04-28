package inc.roguelike.babusya.loot

import inc.roguelike.babusya.controllers.HeroActionController
import inc.roguelike.babusya.effects.HealEffect
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.mocks.MockInputListener
import inc.roguelike.babusya.mocks.MockMap
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

class PotionTest {
    @Test
    fun potionTest() {
        val hero = Hero(
            actionController = HeroActionController(MockMap(), MockInputListener(LinkedList())),
            creatureCharacteristics = CreatureCharacteristics(100, 200, 100),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h"
        )
        val potion = Potion("Heal potion", HealEffect(75))
        potion.use(hero.inventory)
        assertEquals(175, hero.characteristics.hitPoints)
        assertEquals(200, hero.characteristics.maxHitPoints)

        potion.use(hero.inventory)
        assertEquals(200, hero.characteristics.hitPoints)
        assertEquals(200, hero.characteristics.maxHitPoints)
    }

}
