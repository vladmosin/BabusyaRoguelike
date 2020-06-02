package inc.roguelike.babusya.effects

import inc.roguelike.babusya.effects.memento.EffectMemento
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.element.concrete.*
import inc.roguelike.babusya.visitors.ElementVisitor

/**
 * Interface for effects implementing visitor pattern and able to affect game elements
 * */
interface Effect : ElementVisitor<Boolean> {
    /**
     * Effect on visiting wall
     * */
    override fun visitWall(wall: Wall): Boolean = false

    /**
     * Effect on visiting empty game element
     * */
    override fun visitEmptyGameElement(emptyGameElement: EmptyGameElement): Boolean = false

    /**
     * Effect on visiting hero
     * */
    override fun visitHero(hero: Hero): Boolean = false

    /**
     * Effect on visiting monster
     * */
    override fun visitMonster(monster: Monster): Boolean = false

    /**
     * Effect on visiting confused
     * */
    override fun visitConfused(decorableCreature: DecorableCreature): Boolean = decorableCreature.creature.accept(this)

    /**
     * Applying effect to given game element
     * */
    fun apply(gameElement: GameElement): Boolean = gameElement.accept(this)

    /**
     * Returns description
     * */
    fun getDescription(from: GameElement?, to: GameElement?): String

    /**
     * Serializes effect
     * */
    fun serialize(): String

    companion object {
        /**
         * Deserializes effect
         * */
        fun deserialize(line: String): Effect? {
            return EffectMemento.deserialize(line)
        }
    }
}
