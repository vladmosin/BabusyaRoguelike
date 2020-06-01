package inc.roguelike.babusya.map.memento

import InputListener
import inc.roguelike.babusya.collectToString
import inc.roguelike.babusya.controllers.ControllerFactory
import inc.roguelike.babusya.getArguments
import inc.roguelike.babusya.getName
import inc.roguelike.babusya.map.Cell
import inc.roguelike.babusya.map.rectangularMap.RectangularMap


/**
 * Memento for rectangular map
 * */
class RectangularMapMemento {
    companion object {
        private const val name = "RectangularMap"

        /**
         * Creates RectangularMap from string
         * */
        fun deserialize(line: String, inputListener: InputListener): RectangularMap? {
            val name = getName(line)
            val args = getArguments(line)

            if (name == null || args == null || args.size < 2 || name != this.name) {
                return null
            }

            try {
                val height = args[0].toInt()
                val width = args[1].toInt()

                if (args.size != height * width + 2) {
                    return null
                }

                val map = RectangularMap(Array(height) { Array(width) { Cell() } })
                val controllerFactory = ControllerFactory(map, inputListener)

                for (i in 0 until height) {
                    for (j in 0 until width) {
                        val cell = Cell.deserialize(controllerFactory, args[2 + i * width + j])
                        if (cell == null) {
                            return null
                        } else {
                            map.getRectangle()[i][j].storedItem = cell.storedItem
                            map.getRectangle()[i][j].loot = cell.loot
                        }
                    }
                }

                map.updateControllers()

                return map

            } catch (e: NumberFormatException) {
                return null
            }
        }

        /**
         * Serializes map
         * */
        fun serialize(map: RectangularMap): String {
            val args = ArrayList<String>()

            args.add(map.height.toString())
            args.add(map.width.toString())

            for (i in 0 until map.height) {
                for (j in 0 until map.width) {
                    args.add(map.getRectangle()[i][j].serialize())
                }
            }

            return collectToString(name, args)
        }
    }
}