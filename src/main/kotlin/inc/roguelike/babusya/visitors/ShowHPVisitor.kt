package inc.roguelike.babusya.visitors

import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.graphics.TextGraphics
import inc.roguelike.babusya.element.abstracts.AbstractCreature
import inc.roguelike.babusya.element.concrete.*

/**
 * Visitor, which shows inventory on console
 * */
class ShowHPVisitor(
    private val textGraphics: TextGraphics
) : ElementVisitor<Unit> {

    var xOffset = 0
    var yOffset = 0

    /**
     * Visits wall
     * */
    override fun visitWall(wall: Wall) {
    }

    /**
     * Visits empty game element
     * */
    override fun visitEmptyGameElement(emptyGameElement: EmptyGameElement) {
    }

    private fun visitAbstractCreature(creature: AbstractCreature) {
        textGraphics.foregroundColor = TextColor.ANSI.WHITE
        textGraphics.putString(xOffset, yOffset, "${creature.id}")
        textGraphics.putString(xOffset + 19, yOffset, ":")
        textGraphics.foregroundColor = TextColor.ANSI.GREEN
        textGraphics.putString(xOffset + 20, yOffset,
            "${creature.characteristics.hitPoints} hp")

        yOffset++
    }

    /**
     * Visits hero
     * */
    override fun visitHero(hero: Hero) {
        visitAbstractCreature(hero)
    }

    /**
     * Visits monster
     * */
    override fun visitMonster(monster: Monster) {
        visitAbstractCreature(monster)
    }

    /**
     * Visits wall
     * */
    override fun visitConfused(decorableCreature: DecorableCreature) {
        visitAbstractCreature(decorableCreature.creature as AbstractCreature)
    }
}
