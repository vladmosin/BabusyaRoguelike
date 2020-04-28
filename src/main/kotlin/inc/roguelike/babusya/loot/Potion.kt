package inc.roguelike.babusya.loot

import inc.roguelike.babusya.effects.Effect
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.interfaces.GameElement

class Potion(val name: String, val effect: Effect): Consumable {
    override fun getDescrition(): String {
        return "(Po| name: $name)"
    }

    override fun use(inventory: Inventory) {
        effect.apply(inventory.owner)
    }
}
