package inc.roguelike.babusya.loot

import inc.roguelike.babusya.effects.Effect

/**
 * Applies effect on use
 */
class Potion(val name: String, val effect: Effect): Consumable {
    override fun use(inventory: Inventory) {
        effect.apply(inventory.owner)
        inventory.removeFromInventory(this)
    }
}
