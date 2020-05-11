package inc.roguelike.babusya.UI.overture

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.ButtonBar
import javafx.scene.text.FontWeight
import tornadofx.*

class HelloWorld: View() {
    val controller: HelloWorldController by inject()
    val input = SimpleStringProperty()

    override val root = form {
        fieldset {
            field("Input") {
                textfield(input) {
                    promptText = "Input lol"
                }
            }
            button("Commit") {
                action {
                    controller.writeToDb(input.value)
                    input.value = ""
                }
            }
            button("Go to next page") {
                action {
                    replaceWith<SecondPage>(
                        ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT)
                    )
                }
            }
        }
    }
}

class SecondPage: View() {
    override val root = hbox {
        label {
            text = "Hi everybody"
        }
    }
}

class HelloWorldController: Controller() {
    fun writeToDb(inputValue: String) {
        println("Writing $inputValue to database!")
    }
}

class HelloWorldApp: App(HelloWorld::class)


class MainView : View("Basic form") {
    val model = ViewModel()
    val username = model.bind { SimpleStringProperty() }
    val password = model.bind { SimpleStringProperty() }

    override val root = form {
        fieldset {
            field("username") {
                textfield(username).required()
            }
            field("password") {
                passwordfield(password).required()
            }
            buttonbar {
                button("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE).setOnAction {
                    println("do nothing")
                }
                button("OK", ButtonBar.ButtonData.OK_DONE).setOnAction {
                    model.commit {
                        println("do something")
                    }
                }
            }
        }
    }
}

class Styles : Stylesheet() {
    init {
        label {
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            backgroundColor += c("#aaaaaa")
        }
    }
}

