package inc.roguelike.babusya.UI.overture

import inc.roguelike.babusya.FileSystem
import inc.roguelike.babusya.levels.LevelCreator
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import tornadofx.*

class SinglePlayerMenu: View("SinglePlayer") {
    override val root =
        vbox(UserInteractionApp.MENUS_SPACING, Pos.TOP_CENTER) {
            paddingAll = UserInteractionApp.MENUS_PADDING_ALL
            button("Generate random level") {
                useMaxSize = true
                vgrow = Priority.ALWAYS
                maxHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                action {

                }
            }
            button("Load campaign level") {
                useMaxSize = true
                vgrow = Priority.ALWAYS
                maxHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                action {

                }
            }
            if (FileSystem.fileExists(LevelCreator.SAVED_PATH)) {
                button("Load saved level") {
                    useMaxSize = true
                    vgrow = Priority.ALWAYS
                    maxHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                    action {

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
