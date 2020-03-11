package inc.roguelike.babusya

class GameState {
    private var currentLevel = 0
    private var didGameEnd = false

    fun didGameEnd(): Boolean {
        return didGameEnd
    }

    fun endGame() {
        didGameEnd = true
    }
}