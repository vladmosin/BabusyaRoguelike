package inc.roguelike.babusya

import java.io.File

/**
 * static functions for reading and saving files
 * */
abstract class FileSystem {
    companion object {
        fun loadFile(path: String): String {
            return File(path).readLines().joinToString(separator = "\n")
        }

        fun saveToFile(path: String, content: String) {
            File(path).printWriter().use { out -> out.write(content) }
        }
    }
}