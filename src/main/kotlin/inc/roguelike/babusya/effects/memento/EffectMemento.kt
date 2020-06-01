package inc.roguelike.babusya.effects.memento

import inc.roguelike.babusya.effects.*

class EffectMemento {
    companion object {
        fun deserialize(line: String): Effect? {
            val deserializers = listOf(
                { s: String -> ConfusionChanceEffect.deserialize(s) },
                { s: String -> HealEffect.deserialize(s) },
                { s: String -> MonsterPunchEffect.deserialize(s) },
                { s: String -> PunchEffect.deserialize(s) }

            )

            for (deserializer in deserializers) {
                val gameElement = deserializer(line)
                if (gameElement != null) {
                    return gameElement
                }
            }

            return null
        }
    }
}