package inc.roguelike.babusya.map.rectangularMap

import InputListener
import inc.roguelike.babusya.controllers.*
import inc.roguelike.babusya.element.*
import inc.roguelike.babusya.element.interfaces.Creature
import inc.roguelike.babusya.element.CreatureCharacteristics
import inc.roguelike.babusya.element.concrete.ConfusableCreature
import inc.roguelike.babusya.element.concrete.Hero
import inc.roguelike.babusya.element.concrete.Monster
import inc.roguelike.babusya.element.concrete.Wall
import inc.roguelike.babusya.map.Cell
import kotlin.random.Random

/**
 * Generates random RectangularMap
 * */
class RectangularMapBuilder(
    private val height: Int,
    private val width: Int
) {
    private val rectangle = Array(height) { Array(width) { Cell() } }
    private var creatureToController : ArrayList<Pair<Creature, ControllerType>> = ArrayList()
    private var heroElement: Hero? = null

    /**
     * Creates map
     * */
    fun buildMap(inputListener: InputListener): RectangularMap {
        val map = RectangularMap(rectangle)
        val controllerFactory = ControllerFactory(map, inputListener)
        for ((creature, controllerType) in creatureToController) {
            creature.actionController = controllerFactory.createController(controllerType)
            when (creature.actionController) {
                is AggressiveController -> (creature.actionController as AggressiveController).attackTarget = heroElement
                is CowardController -> (creature.actionController as CowardController).scaryElement = heroElement
            }
        }
        // Ugly, TODO refactor :(
        for (cell in map) {
            if (cell.storedItem is Monster) {
                cell.storedItem = ConfusableCreature(cell.storedItem as Creature, controllerFactory.createController(ControllerType.RandomController))
            }
        }
        return map
    }

    private fun getEmptyPositions(): ArrayList<Pair<Int, Int>> {
        val emptyPositions = ArrayList<Pair<Int, Int>>()
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (!rectangle[i][j].storesActiveItem()) {
                    emptyPositions.add(Pair(i, j))
                }
            }
        }
        return emptyPositions
    }

    /**
     * Adds hero to the map
     * */
    fun addHero(): RectangularMapBuilder {
        val emptyPositions = getEmptyPositions()
        val emptyCellsNumber = emptyPositions.size
        check(emptyCellsNumber > 0) { "There is no place for hero" }
        val (hi, hj) = emptyPositions[Random.nextInt(emptyCellsNumber)]

        val hero = Hero(
            actionController = null,
            creatureCharacteristics = CreatureCharacteristics.createBasic(),
            elementStatus = ElementStatus.ALIVE,
            experience = 0,
            id = "h"
        )
        heroElement = hero
        rectangle[hi][hj].storedItem = hero
        creatureToController.add(Pair(hero, ControllerType.HeroController))

        return this
    }

    /**
     * Adds walls to the map
     * */
    fun addWalls(): RectangularMapBuilder {
        val emptyPositions = getEmptyPositions()
        emptyPositions.shuffle()
        val wallsNumber = emptyPositions.size / 4
        for (q in 0 until wallsNumber) {
            val (wi, wj) = emptyPositions[q]
            val wall = Wall("w${q + 1}", ElementStatus.ALIVE)
            rectangle[wi][wj].storedItem = wall
        }
        return this
    }

    fun addMonsters(): RectangularMapBuilder {
        val emptyPositions = getEmptyPositions()
        emptyPositions.shuffle()
        if (emptyPositions.size >= 3) {
            run {
                val (i, j) = emptyPositions[0]
                val monster = Monster(
                    creatureCharacteristics = CreatureCharacteristics(
                        100,
                        100,
                        1
                    ),
                    actionController = null,
                    id = "PinkiePie",
                    elementStatus = ElementStatus.ALIVE
                )
                rectangle[i][j].storedItem = monster
                creatureToController.add(Pair(monster, ControllerType.PassiveController))
            }

            run {
                val (i, j) = emptyPositions[1]
                val monster = Monster(
                    creatureCharacteristics = CreatureCharacteristics(
                        100,
                        100,
                        1
                    ),
                    actionController = null,
                    id = "RainbowDash",
                    elementStatus = ElementStatus.ALIVE
                )
                rectangle[i][j].storedItem = monster
                creatureToController.add(Pair(monster, ControllerType.AggressiveController))
            }

            run {
                val (i, j) = emptyPositions[2]
                val monster = Monster(
                    creatureCharacteristics = CreatureCharacteristics(
                        100,
                        100,
                        1
                    ),
                    actionController = null,
                    id = "Fluttershy",
                    elementStatus = ElementStatus.ALIVE
                )
                rectangle[i][j].storedItem = monster
                creatureToController.add(Pair(monster, ControllerType.CowardController))
            }
        }
        return this
    }
}
