package inc.roguelike.babusya

import java.lang.Integer.max

class GameLog {
    private val log = ArrayList<String>()

    fun add(message: String) {
        log.add(message)
    }

    fun last(count: Int?) : List<String> {
        if (count == null) return log
        return log.subList(max(0, log.size - count), log.size)
    }
}