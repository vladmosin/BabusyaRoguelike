package inc.roguelike.babusya.UI.overture

import javafx.stage.Stage
import tornadofx.*

class UserInteractionApp: App(MainMenu::class) {
    companion object {
        const val PREFERRED_WIDTH = 400.0
        const val PREFERRED_HEIGHT = 500.0
        const val MIN_WIDTH = 350.0
        const val MIN_HEIGHT = 400.0
        const val MENUS_PADDING_ALL = 30.0
        const val MENUS_SPACING = 20.0
        const val BUTTON_MAX_HEIGHT = 100.0
    }

    override fun start(stage: Stage) {
        stage.width = PREFERRED_WIDTH
        stage.height = PREFERRED_HEIGHT
        stage.minHeight = MIN_HEIGHT
        stage.minWidth = MIN_WIDTH
        super.start(stage)
    }
}
