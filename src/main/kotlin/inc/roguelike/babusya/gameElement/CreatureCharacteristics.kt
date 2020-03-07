package inc.roguelike.babusya.gameElement

data class CreatureCharacteristics(var hitPoints: Int, var maxHitPoints: Int, var attack: Int) {
    companion object {
        fun createBasic(): CreatureCharacteristics {
            val maxHitPoints = 100
            val attack = 20

            return CreatureCharacteristics(hitPoints = maxHitPoints, maxHitPoints = maxHitPoints, attack = attack)
        }
    }
}