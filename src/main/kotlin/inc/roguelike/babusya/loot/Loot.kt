package inc.roguelike.babusya.loot

/**
 * Item which can be collected and used by hero
 */
interface Loot {
    fun use(inventory: Inventory)
}
