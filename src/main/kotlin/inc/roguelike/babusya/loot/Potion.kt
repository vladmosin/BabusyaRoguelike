package inc.roguelike.babusya.loot

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.interfaces.GameElement

class Potion(val name: String, val effect: Effect): Consumable {
    override fun use(inventory: Inventory) {
        effect.apply(inventory.owner)
    }

    override fun serialize(): String {
        TODO()
    }

    companion object {
        private const val name = "Potion"

        fun deserialize(line: String): Potion? {
            TODO()
        }
    }
}
