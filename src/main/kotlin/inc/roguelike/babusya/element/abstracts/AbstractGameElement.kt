package inc.roguelike.babusya.element.abstracts

import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.element.ElementStatus
import inc.roguelike.babusya.element.ElementStatusObserver
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.interfaces.GameElement
import inc.roguelike.babusya.element.interfaces.StaticElement
import inc.roguelike.babusya.visitors.ElementVisitor

/**
 * Implementation of basic methods for game element
 * */
abstract class AbstractGameElement(override val id: String, elementStatus: ElementStatus) : GameElement {

    private val observers = ArrayList<ElementStatusObserver>()

    override var elementStatus: ElementStatus = elementStatus
        set(value) {
            field = value
            for (observer in observers)
                observer.onStatusUpdate(this)
        }

    override fun attachStatusObserver(observer: ElementStatusObserver) {
        observers.add(observer)
    }

    override fun attackEffects() : List<Effect> {
        return emptyList()
    }

    override fun defensiveEffects(): List<Effect> {
        return emptyList()
    }

    companion object {
        fun deserialize(controllerFactory: ControllerFactory, string: String): GameElement? {
            val deserializers = listOf(
                { s: String -> Creature.deserialize(controllerFactory, s)},
                { s: String -> StaticElement.deserialize(s) })

            for (deserializer in deserializers) {
                val gameElement = deserializer(string)
                if (gameElement != null) {
                    return gameElement
                }
            }

            return null
        }
    }
}
