package inc.roguelike.babusya.loot

import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.loot.memento.PotionMemento

/**
 * Applies effect on use
 */
class Potion(val name: String, val effect: Effect): Consumable {
    /**
     * Returns description
     * */
    override fun getDescrition(): String {
        return "(Po| name: $name)"
    }

    /**
     * Uses given inventory
     * */
    override fun use(inventory: Inventory) {
        effect.apply(inventory.owner)
        inventory.removeFromInventory(this)
    }

    /**
     * Serializes potion
     * */
    override fun serialize() = PotionMemento.serialize(this)

    companion object {
        /**
         * Deserializes potion
         * */
        fun deserialize(line: String): Potion? {
            return PotionMemento.deserialize(line)
        }
    }
}
