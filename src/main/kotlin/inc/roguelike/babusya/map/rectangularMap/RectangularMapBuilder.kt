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
import kotlin.math.abs
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
                is AggressiveController ->
                    (creature.actionController as AggressiveController).attackTarget = heroElement
                is CowardController ->
                    (creature.actionController as CowardController).scaryElement = heroElement
            }
        }
        // Ugly, TODO refactor :(
        for (cell in map) {
            if (cell.storedItem is Monster) {
                cell.storedItem = ConfusableCreature(
                    cell.storedItem as Creature,
                    controllerFactory.createController(ControllerType.RandomController)
                )
            }
        }
        return map
    }

    private fun getPositions(predicate: (cell: Cell) -> Boolean): ArrayList<Pair<Int, Int>> {
        val positions = ArrayList<Pair<Int, Int>>()
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (predicate(rectangle[i][j])) {
                    positions.add(Pair(i, j))
                }
            }
        }
        return positions
    }

    /**
     * Adds hero to the map
     * */
    fun addHero(): RectangularMapBuilder {
        val emptyPositions = getPositions { cell -> !cell.storesActiveItem() }
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

    private fun fillRandomShortestPath(
        used: Array<Array<Boolean>>,
        start: Pair<Int, Int>,
        finish: Pair<Int, Int>
    ) {
        val di = if (start.first < finish.first) +1 else -1
        val dj = if (start.second < finish.second) +1 else -1
        val distance = abs(start.first - finish.first) + abs(start.second - finish.second)
        var (i, j) = start
        used[i][j] = true
        for (step in 0 until distance) {
            if (i != finish.first && Random.nextBoolean()) {
                i += di
            } else {
                j += dj
            }
            used[i][j] = true
        }
    }

    private fun calculateMoveProbability(used: Array<Array<Boolean>>, i: Int, j: Int): Double {
        var usedCount = 0
        for (di in -1 until 2) {
            for (dj in -1 until 2) {
                val vi = i + di
                val vj = j + dj
                if ((vi in 0 until height) && (vj in 0 until width)) {
                    if (used[vi][vj]) {
                        usedCount++
                    }
                }
            }
        }
        return 0.7 * (1.0 - usedCount / 9.0)
    }

    private fun randomExpansion(used: Array<Array<Boolean>>, vi: Int, vj: Int) {
        val directions = arrayListOf(Pair(-1, 0), Pair(+1, 0), Pair(0, -1), Pair(0, +1))
        for ((di, dj) in directions) {
            val ti = vi + di
            val tj = vj + dj
            if (ti < 0 || ti >= height || tj < 0 || tj >= width || used[ti][tj]) {
                continue
            }
            if (Random.nextDouble(0.0, 1.0) < calculateMoveProbability(used, ti, tj)) {
                used[ti][tj] = true
                randomExpansion(used, ti, tj)
            }
        }
    }

    fun addWalls(): RectangularMapBuilder {
        val importantPositions = getPositions {
                cell -> cell.storesActiveItem() ||
                Random.nextDouble(0.0, 1.0) < 10.0 / height / width
        }
        val used = Array(height) { Array(width) { false } }
        for (i in 0 until importantPositions.size) {
            if (i + 1 < importantPositions.size) {
                fillRandomShortestPath(used, importantPositions[i], importantPositions[i + 1])
            }
            used[importantPositions[i].first][importantPositions[i].second] = true
        }
        for ((si, sj) in importantPositions) {
            if (Random.nextBoolean()) {
                randomExpansion(used, si, sj)
            }
        }
        var wallId = 1
        for (i in 0 until height) {
            for (j in 0 until width) {
                if (!used[i][j]) {
                    rectangle[i][j].storedItem = Wall("w${wallId}", ElementStatus.ALIVE)
                    wallId++
                }
            }
        }
        return this
    }

    fun addMonsters(): RectangularMapBuilder {
        val emptyPositions = getPositions { cell -> !cell.storesActiveItem() }
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
