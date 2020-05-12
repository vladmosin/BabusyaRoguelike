package inc.roguelike.babusya.UI.overture

import inc.roguelike.babusya.Game
import inc.roguelike.babusya.levels.LevelInfo
import inc.roguelike.babusya.levels.LevelsType
import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.control.SelectionMode
import javafx.scene.layout.Priority
import tornadofx.*

class RoomsMenu: View() {
    val controller: OvertureController by inject()

    override val root = vbox(UserInteractionApp.MENUS_SPACING, Pos.TOP_CENTER) {
        useMaxSize = true
        hgrow = Priority.ALWAYS
        vgrow = Priority.ALWAYS
        paddingAll = UserInteractionApp.MENUS_PADDING_ALL

        val listOfRooms = listview<String> {
            vgrow = Priority.ALWAYS
            useMaxSize = true
            selectionModel.selectionMode = SelectionMode.SINGLE
            items.addAll("Room 23", "Room 9")
        }

        hbox {
            alignment = Pos.CENTER
            useMaxSize = true
            hgrow = Priority.ALWAYS
            spacing = 20.0
            button("Back") {
                useMaxSize = true
                vgrow = Priority.ALWAYS
                fitToParentWidth()
                maxHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                prefHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                action {
                    controller.disconnectFromServer()
                    replaceWith<MultiplayerMenu>(ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
                }
            }
            button("Update") {
                useMaxSize = true
                vgrow = Priority.ALWAYS
                fitToParentWidth()
                maxHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                prefHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                action {
                    listOfRooms.items.setAll(controller.getRooms().map { id -> "Room $id" })
                }
            }
            button("Play") {
                useMaxSize = true
                vgrow = Priority.ALWAYS
                fitToParentWidth()
                maxHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                prefHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                action {
                    val selectedRoom = listOfRooms.selectedItem
                    if (selectedRoom != null) {
                        val id = selectedRoom.removePrefix("Room ").toInt()
                        runAsync { controller.startMultiplayerGame(id); }
                        primaryStage.close()
                    }
                }
            }
        }
    }

}
