package inc.roguelike.babusya.UI.overture

import inc.roguelike.babusya.Game
import inc.roguelike.babusya.levels.LevelInfo
import inc.roguelike.babusya.levels.LevelsType
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.control.SelectionMode
import javafx.scene.layout.Priority
import javafx.util.Duration
import tornadofx.*

class RoomsMenu: View() {
    val controller: OvertureController by inject()
    val selectedRoom = SimpleStringProperty()

    override val root = vbox(UserInteractionApp.MENUS_SPACING, Pos.TOP_CENTER) {
        useMaxSize = true
        hgrow = Priority.ALWAYS
        vgrow = Priority.ALWAYS
        paddingAll = UserInteractionApp.MENUS_PADDING_ALL

        val listOfRooms = listview<String> {
            bindSelected(selectedRoom)
            vgrow = Priority.ALWAYS
            useMaxSize = true
            selectionModel.selectionMode = SelectionMode.SINGLE
            items.addAll("Room 23", "Room 9")
        }

        hbox {
            alignment = Pos.CENTER
            useMaxSize = true
            hgrow = Priority.ALWAYS
            spacing = 15.0
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
            button("Create") {
                useMaxSize = true
                vgrow = Priority.ALWAYS
                fitToParentWidth()
                maxHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                prefHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                action {
//                    val (success, message) = controller.createRoom()
                    val success = false
                    val message = "?"
                    if (success) {
                        listOfRooms.items.setAll(controller.getRooms().map { id -> "Room $id" })
                    }  else {
                        warning("Server did not create new room\n$message")
                    }
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
                enableWhen(selectedRoom.isNotNull)
                useMaxSize = true
                vgrow = Priority.ALWAYS
                fitToParentWidth()
                maxHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                prefHeight = UserInteractionApp.BUTTON_MAX_HEIGHT
                action {
                    val id = selectedRoom.value.removePrefix("Room ").toInt()
                    runAsync { controller.startMultiplayerGame(id); }
                    primaryStage.close()
                }
            }
        }
    }

}
