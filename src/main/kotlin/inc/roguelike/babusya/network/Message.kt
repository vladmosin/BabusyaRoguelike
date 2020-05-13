package inc.roguelike.babusya.network

import inc.roguelike.babusya.GameLog

/**
 * Stores information sending to client
 * */
data class Message(val serializedLevel: String, val gameEnds: Boolean, val serializedGameLog: String)