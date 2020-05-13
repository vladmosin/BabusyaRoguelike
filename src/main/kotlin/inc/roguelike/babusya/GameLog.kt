package inc.roguelike.babusya

import java.lang.Integer.max


/**
 * Implementation of game log
 * */
class GameLog {
    private val log = ArrayList<String>()

    fun add(message: String) {
        log.add(message)
    }

    fun last(count: Int?) : List<String> {
        if (count == null) return log
        return log.subList(max(0, log.size - count), log.size)
    }

    fun serialize() = collectToString(name, log)

    companion object {
        private val name = "GameLog"

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