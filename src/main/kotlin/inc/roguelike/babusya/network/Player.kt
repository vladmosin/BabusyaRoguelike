package inc.roguelike.babusya.network

import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.levels.Level

class Player(val hero: Hero, val client: Client) {
    fun sendMessage(message: Message) {
//        client.sendMessage(message)
    }
}
