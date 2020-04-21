package inc.roguelike.babusya.effects

import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.concrete.Monster
import junit.framework.Assert.assertEquals
import org.junit.Test

class PunchEffectTest {

    @Test
    fun punchNotKill() {
        val monster = Monster(
            CreatureCharacteristics(10, 10, 0),
            null,
            "A",
            ElementStatus.ALIVE
        )

        monster.accept(PunchEffect(5))
        assertEquals(5, monster.characteristics.hitPoints)
        assertEquals(ElementStatus.ALIVE, monster.elementStatus)
    }

    @Test
    fun punchKill() {
        val monster = Monster(
            CreatureCharacteristics(10, 10, 0),
            null,
            "A",
            ElementStatus.ALIVE
        )

        monster.accept(PunchEffect(50))
        assertEquals(0, monster.characteristics.hitPoints)
        assertEquals(ElementStatus.DEAD, monster.elementStatus)
    }
}
