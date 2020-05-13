package inc.roguelike.babusya.UI.overture

import inc.roguelike.babusya.FileSystem
import inc.roguelike.babusya.levels.LevelCreator
import inc.roguelike.babusya.levels.LevelInfo
import inc.roguelike.babusya.levels.LevelsType
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import tornadofx.*

class SinglePlayerMenu: View("SinglePlayer") {
    val controller: OvertureController by inject()
    override val root =
        vbox(UserInteractionApp.MENUS_SPACING, Pos.TOP_CENTER) {
            paddingAll = UserInteractionApp.MENUS_PADDING_ALL
            button("Generate random level") {
                useMaxSize = true
                vgrow = Priority.ALWAYS
                maxHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                action {
                    runAsync { controller.startSinglePlayerGame(LevelInfo(1, LevelsType.GENERATED))}
                    primaryStage.close()
                }
            }
            button("Load campaign level") {
                useMaxSize = true
                vgrow = Priority.ALWAYS
                maxHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                action {
                    replaceWith<LoadLevelMenu>(ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
                }
            }
            if (FileSystem.fileExists(LevelCreator.SAVED_PATH)) {
                button("Load saved level") {
                    useMaxSize = true
                    vgrow = Priority.ALWAYS
                    maxHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                    action {
                        runAsync { controller.startSinglePlayerGame(LevelInfo(1, LevelsType.SAVED))}
                        primaryStage.close()
                    }
                }
            }
            button("Back") {
                useMaxSize = true
                vgrow = Priority.ALWAYS
                maxHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                action {
                    replaceWith<MainMenu>(ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
                }
            }
        }
}
