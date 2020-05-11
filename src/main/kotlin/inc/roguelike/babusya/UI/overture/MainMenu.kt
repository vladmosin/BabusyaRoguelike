package inc.roguelike.babusya.UI.overture

import inc.roguelike.babusya.UI.overture.UserInteractionApp.Companion.BUTTON_MAX_HEIGHT
import javafx.beans.binding.BooleanExpression
import javafx.beans.value.ObservableBooleanValue
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.layout.Priority
import tornadofx.*

class MainMenu: View(title="Main Menu") {
    override val root =
        vbox(UserInteractionApp.MENUS_SPACING, Pos.TOP_CENTER) {
            paddingAll = UserInteractionApp.MENUS_PADDING_ALL
            button("SinglePlayer") {
                useMaxSize = true
                vgrow = Priority.ALWAYS
                maxHeight = BUTTON_MAX_HEIGHT
                action {
                    replaceWith<SinglePlayerMenu>(ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
                }
            }
            button("Multiplayer") {
                useMaxSize = true
                vgrow = Priority.ALWAYS
                maxHeight = BUTTON_MAX_HEIGHT
                action {
                    replaceWith<MultiplayerMenu>(ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
                }
            }
        }
}
