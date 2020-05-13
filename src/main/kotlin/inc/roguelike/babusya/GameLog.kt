package inc.roguelike.babusya

import java.lang.Integer.max


/**
 * Implementation of game log
 * */
class GameLog {
    private val log = ArrayList<String>()

    /**
     * Adds new message to game log
     * */
    fun add(message: String) {
        log.add(message)
    }

    /**
     * Returns last count messages
     * */
    fun last(count: Int?) : List<String> {
        if (count == null) return log
        return log.subList(max(0, log.size - count), log.size)
    }

    /**
     * Serializes game log
     * */
    fun serialize() = collectToString(name, log)

    companion object {
        private val name = "GameLog"

        /**
         * Deserializes game log
         * */
        fun deserialize(line: String): GameLog? {
            val name = getName(line)
            val args = getArguments(line)

            if (name == null || name != this.name || args == null) {
                return null
            }

            val gameLog = GameLog()
            gameLog.log.addAll(args)

            return gameLog
        }
    }
}