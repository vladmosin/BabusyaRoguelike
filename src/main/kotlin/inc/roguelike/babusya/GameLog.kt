package inc.roguelike.babusya

import java.lang.Integer.max


/**
 * Implementation of game log
 * */
class GameLog {
    private val log = ArrayList<String>()

    /**
     * Adds message to game log
     * */
    fun add(message: String) {
        log.add(message)
    }

    /**
     * Returns last message
     * */
    fun last(count: Int?) : List<String> {
        if (count == null) return log
        return log.subList(max(0, log.size - count), log.size)
    }
}