package inc.roguelike.babusya.loot

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.effects.Effect

/**
 * Applies effect on use
 */
class Potion(val name: String, val effect: Effect): Consumable {
    override fun use(inventory: Inventory) {
        effect.apply(inventory.owner)
        inventory.removeFromInventory(this)
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
