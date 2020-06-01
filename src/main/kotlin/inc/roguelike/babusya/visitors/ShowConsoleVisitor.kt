package inc.roguelike.babusya.visitors

import inc.roguelike.babusya.element.concrete.*
import com.googlecode.lanterna.TextColor
/**
 * Visitor, which shows elements to console
 * */
class ShowConsoleVisitor : ElementVisitor<Pair<Char, TextColor>> {
    override fun visitWall(wall: Wall): Pair<Char, TextColor> {
        return Pair('#', TextColor.ANSI.WHITE)
    }

    override fun visitEmptyGameElement(emptyGameElement: EmptyGameElement): Pair<Char, TextColor> {
        return Pair('.', TextColor.ANSI.WHITE)
    }

    override fun visitHero(hero: Hero): Pair<Char, TextColor> {
        return Pair('@', TextColor.ANSI.GREEN)
    }

    override fun visitMonster(monster: Monster): Pair<Char, TextColor> {
        return Pair(if (monster.id.isNotEmpty()) monster.id.first() else 'M', TextColor.ANSI.RED)
    }

    override fun visitConfused(decorableCreature: DecorableCreature): Pair<Char, TextColor> {
        return decorableCreature.creature.accept(this)
    }
}
