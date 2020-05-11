package inc.roguelike.babusya.UI.overture

import inc.roguelike.babusya.UI.overture.UserInteractionApp.Companion.MENUS_PADDING_ALL
import inc.roguelike.babusya.UI.overture.UserInteractionApp.Companion.MENUS_SPACING
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import tornadofx.*

class MultiplayerMenu: View("Multiplayer") {
    val model = ViewModel()
    val login = model.bind { SimpleStringProperty() }
    val address = model.bind { SimpleStringProperty() }
    val port = model.bind { SimpleStringProperty() }
    override val root =
        form {
            fieldset("Connect to server", labelPosition = Orientation.VERTICAL) {
                spacing = MENUS_SPACING
                paddingAll = MENUS_PADDING_ALL
                field("Login", Orientation.VERTICAL) {
                    textfield(login) {
                        promptText = "Login"
                    }.validator {
                        if (it.isNullOrBlank()) error("Login is required") else {
                            val regex = Regex("[a-zA-Z0-9]+")
                            if (!regex.matches(it)) error("Only English letters and digits can be used") else null
                        }
                    }
                }
                field("Address", Orientation.VERTICAL) {
                    textfield(address) {
                        promptText = "Server address"
                    }.validator {
                        if (it.isNullOrBlank()) error("Server address is required") else null
                    }
                }
                field("Port", Orientation.VERTICAL) {
                    textfield(port) {
                        promptText = "Server Port"
                    }.validator {
                        if (it.isNullOrBlank()) {
                            error("Port is required")
                        } else {
                            if (!it.isInt()) error("Port value should be an integer") else {
                                if (it.toInt() !in 0..65535) error("Port value should be an integer between 0 and 65535") else null
                            }
                        }
                    }
                }
                field {
                    button("Back") {
                        useMaxSize = true
                        action {
                            replaceWith<MainMenu>(ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
                        }
                    }
                    button("Connect") {
                        useMaxSize = true
                        action {
                            model.commit {
                                println("Commit")
                            }
                        }
                    }
                }
            }
        }
}
