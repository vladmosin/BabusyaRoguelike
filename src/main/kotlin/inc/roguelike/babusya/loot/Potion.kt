package inc.roguelike.babusya.loot

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName

/**
 * Applies effect on use
 */
class Potion(val name: String, val effect: Effect): Consumable {
    override fun getDescrition(): String {
        return "(Po| name: $name)"
    }

    override fun use(inventory: Inventory) {
        effect.apply(inventory.owner)
        inventory.removeFromInventory(this)
    }

    override fun serialize(): String {
        return collectToString(className, listOf(name, effect.serialize()))
    }

    companion object {
        private const val className = "Potion"

        fun deserialize(line: String): Potion? {
            val name = getName(line)
            val args = getArguments(line)

            return if (name == null || args == null || name != className || args.size != 2) {
                null
            } else {
                val effect = Effect.deserialize(args[1]) ?: return null
                Potion(args[0], effect)
            }
        }
    }
}
