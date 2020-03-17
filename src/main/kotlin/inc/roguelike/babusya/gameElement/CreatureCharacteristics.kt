package inc.roguelike.babusya.gameElement

/**
 * Stores different characteristics of create
 * */
data class CreatureCharacteristics(var hitPoints: Int, var maxHitPoints: Int, var attack: Int) {
    companion object {
        fun createBasic(): CreatureCharacteristics {
            val maxHitPoints = 100
            val attack = 20

            return CreatureCharacteristics(hitPoints = maxHitPoints, maxHitPoints = maxHitPoints, attack = attack)
        }

        fun deserialize(string: String): CreatureCharacteristics? {
            val parts = string.split("@")
            return if (parts.size != 3) {
                null
            } else {
                try {
                    CreatureCharacteristics(parts[0].toInt(), parts[1].toInt(), parts[2].toInt())
                } catch (e: NumberFormatException) {
                    null
                }
            }
        }
    }

    fun serialize(): String {
        return "${hitPoints}@${maxHitPoints}@${attack}"
    }
}