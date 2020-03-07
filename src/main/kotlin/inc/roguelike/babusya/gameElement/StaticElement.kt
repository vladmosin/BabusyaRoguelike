package inc.roguelike.babusya.gameElement

/**
 * Base class for elements which cannot move
 * */
abstract class StaticElement(id: String, elementStatus: ElementStatus) : GameElement(id, elementStatus)