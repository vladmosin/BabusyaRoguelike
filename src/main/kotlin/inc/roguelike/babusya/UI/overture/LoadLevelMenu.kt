package inc.roguelike.babusya.UI.overture

import inc.roguelike.babusya.Game
import inc.roguelike.babusya.UI.overture.UserInteractionApp.Companion.MENUS_PADDING_ALL
import inc.roguelike.babusya.levels.LevelInfo
import inc.roguelike.babusya.levels.LevelsType
import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.layout.Priority
import tornadofx.*


class LoadLevelMenu: View() {
    val controller: OvertureController by inject()

    private val listOfLevels = vbox(UserInteractionApp.MENUS_SPACING, Pos.TOP_CENTER) {
        useMaxSize = true
        hgrow = Priority.ALWAYS
        vgrow = Priority.ALWAYS

        paddingAll = MENUS_PADDING_ALL
        for (id in 1..Game.SAVED_LEVELS) {
            button("Level $id") {
                useMaxSize = true
                vgrow = Priority.ALWAYS
                maxHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                prefHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                action {
                    runAsync { controller.startGame(LevelInfo(id, LevelsType.LOADED))}
                    primaryStage.close()
                }
            }
        }
        button("Back") {
            useMaxSize = true
            vgrow = Priority.ALWAYS
            maxHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
            prefHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
            action {
                replaceWith<SinglePlayerMenu>(ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
            }
        }
    }
    override val root = scrollpane {
        hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
    }

    init {
        root.content = listOfLevels
        listOfLevels.fitToWidth(root)
    }
}
