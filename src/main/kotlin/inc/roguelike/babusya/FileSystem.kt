package inc.roguelike.babusya

import java.io.File

/**
 * static functions for reading and saving files
 * */
abstract class FileSystem {
    companion object {
        /**
         * Reads file
         * */
        fun loadFile(path: String): String {
            return File(path).readLines().joinToString(separator = "\n")
        }

        /**
         * Saves string to file
         * */
        fun saveToFile(path: String, content: String) {
            File(path).printWriter().use { out -> out.write(content) }
        }

        /**
         * Checks that file exists
         * */
        fun fileExists(path: String): Boolean {
            return File(path).exists()
        }

        /**
         * Deletes file
         * */
        fun deleteFile(path: String) {
            File(path).delete()
        }
    }
}