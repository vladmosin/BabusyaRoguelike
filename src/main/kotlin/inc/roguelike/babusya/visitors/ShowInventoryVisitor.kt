package inc.roguelike.babusya.visitors

import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.graphics.TextGraphics
import com.googlecode.lanterna.screen.Screen
import inc.roguelike.babusya.element.concrete.*

/**
 * Visitor, which shows inventory on console
 * */
class ShowInventoryVisitor(
    private val screen: Screen,
    private val textGraphics: TextGraphics
) : ElementVisitor<Unit> {

    var xOffset = 0
    var yOffset = 0

    override fun visitStairs(stairs: Stairs): Unit {

    }

    override fun visitDoor(door: Door): Unit {
    }

    override fun visitWall(wall: Wall): Unit {
    }

    override fun visitEmptyGameElement(emptyGameElement: EmptyGameElement): Unit {
    }

    override fun visitHero(hero: Hero): Unit {
        var yShift = 0
        for (loot in hero.inventory.inPossesionOf) {
            textGraphics.foregroundColor = TextColor.ANSI.RED
            textGraphics.putString(xOffset, yOffset + yShift, if (hero.inventory.selected == loot) "* " else "  ")

            if (hero.inventory.isEquipped(loot)) {
                textGraphics.foregroundColor = TextColor.ANSI.GREEN
            } else {
                textGraphics.foregroundColor = TextColor.ANSI.WHITE
            }
            textGraphics.putString(xOffset + 2, yOffset + yShift, loot.getDescrition())

            yShift++
        }
        if (hero.inventory.selected == null) {
            textGraphics.foregroundColor = TextColor.ANSI.RED
            textGraphics.putString(xOffset, yOffset + yShift, "* ")
        }
    }

    override fun visitMonster(monster: Monster): Unit {

    }

    override fun visitConfused(confusableCreature: ConfusableCreature): Unit {
    }
}
