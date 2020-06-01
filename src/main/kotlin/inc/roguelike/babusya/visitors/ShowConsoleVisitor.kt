package inc.roguelike.babusya.visitors

import inc.roguelike.babusya.element.concrete.*
import com.googlecode.lanterna.TextColor
/**
 * Visitor, which shows elements to console
 * */
class ShowConsoleVisitor : ElementVisitor<Pair<Char, TextColor>> {
    /**
     * Visits wall
     * */
    override fun visitWall(wall: Wall): Pair<Char, TextColor> {
        return Pair('#', TextColor.ANSI.WHITE)
    }

    /**
     * Visits empty game element
     * */
    override fun visitEmptyGameElement(emptyGameElement: EmptyGameElement): Pair<Char, TextColor> {
        return Pair('.', TextColor.ANSI.WHITE)
    }

    /**
     * Visits hero
     * */
    override fun visitHero(hero: Hero): Pair<Char, TextColor> {
        return Pair('@', TextColor.ANSI.GREEN)
    }

    /**
     * Visits monster
     * */
    override fun visitMonster(monster: Monster): Pair<Char, TextColor> {
        return Pair(if (monster.id.isNotEmpty()) monster.id.first() else 'M', TextColor.ANSI.RED)
    }

    /**
     * Visits confused
     * */
    override fun visitConfused(decorableCreature: DecorableCreature): Pair<Char, TextColor> {
        return decorableCreature.creature.accept(this)
    }
}
