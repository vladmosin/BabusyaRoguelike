package inc.roguelike.babusya.effects

import inc.roguelike.babusya.controllers.RandomActionController
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.DecorableCreature
import inc.roguelike.babusya.element.concrete.Monster
import inc.roguelike.babusya.mocks.MockMap
import junit.framework.Assert.assertEquals
import org.junit.Test

class ConfusionEffectTest {

    @Test
    fun confusionSureEffect() {
        val monster = DecorableCreature(
            Monster(
                CreatureCharacteristics(10, 10, 0),
                null,
                "A",
                ElementStatus.ALIVE
            ),
            RandomActionController(MockMap())
        )
        assertEquals(0, monster.moreStepsWhileConfused)
        monster.accept(ConfusionChanceEffect(1.0, 10))
        assertEquals(10, monster.moreStepsWhileConfused)
        monster.accept(ConfusionChanceEffect(1.0, 5))
        assertEquals(10, monster.moreStepsWhileConfused)
        monster.accept(ConfusionChanceEffect(1.0, 50))
        assertEquals(50, monster.moreStepsWhileConfused)
    }
}
