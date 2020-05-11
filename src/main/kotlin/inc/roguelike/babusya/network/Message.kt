package inc.roguelike.babusya.network

import inc.roguelike.babusya.GameLog

data class Message(val serializedLevel: String, val gameEnds: Boolean, val serializedGameLog: String)