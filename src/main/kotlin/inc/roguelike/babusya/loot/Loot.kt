package inc.roguelike.babusya.loot

interface Loot {
    fun use(inventory: Inventory)

    fun getDescrition(): String
}
