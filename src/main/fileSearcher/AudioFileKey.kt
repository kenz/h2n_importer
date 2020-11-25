package main.fileSearcher

import java.nio.file.Path
import java.util.regex.Pattern

data class AudioFileKey(val baseDir: Path, val baseFile: String) {
    companion object {
        fun createOf(path: Path): AudioFileKey = AudioFileKey(path.parent, getBaseName(path.fileName.toString()))

        fun getBaseName(fileName: String): String {
            val matcher = keyPattern.matcher(fileName)
            if (matcher.find()) {
                return matcher.group(0)
            }
            return ""
        }

        private val keyPattern = Pattern.compile("SR\\d{3}")
    }
}
