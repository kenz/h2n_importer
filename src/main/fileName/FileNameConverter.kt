package main.fileName

import java.io.File
import java.nio.file.Path
import java.text.SimpleDateFormat
import java.util.*

class FileNameConverter(file: File) {
    val nameWithoutExtension: String
    val folderNumber: Int
    val mic: MicType
    val updatedDate: Calendar
    private var _directoryName: String? = null
    var directoryName: String
        get() = _directoryName ?: updatedDate.directoryNameString()
        set(value) {
            _directoryName = value
        }

    init {
        nameWithoutExtension = file.nameWithoutExtension
        updatedDate = Calendar.getInstance().apply {
            time = Date(file.lastModified())
        }
        mic = MicType.toMic(file.name)
        val path = file.path.split("/")
        val dirName = path[path.size - 2]
        val dirNumberStr = dirName.replace(NOT_NUMBER_REGEX, "")
        folderNumber = try {
            dirNumberStr.toInt()
        } catch (e: Exception) {
            print(e)
            0
        }
    }

    fun getFileName(extension: Extension): String {
        return "%s%s.%s".format(
            updatedDate.fileNameString(),
            mic.fileNameString(),
            extension.name
        )
    }

    fun getPath(parentDir: Path, extension: Extension) {
        parentDir.resolve(getFileName(extension))
    }

    private fun getPath(parentDir: Path, index: Int? = null) {

    }


    companion object {
        private val NOT_NUMBER_REGEX = Regex("[^0-9]")
    }
}
