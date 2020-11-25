package main.fileName

import java.io.File
import java.lang.IllegalArgumentException
import java.nio.file.Path
import java.util.*

class FileCopyOrder(originalFile: File) {
    /**
     * 2 when FOLDER02
     */
    private val originalDirectoryNumber: Int
    val originalFiles = ArrayList<File>()

    init {
        val dirName = originalFile.parent
        val dirNumberStr = dirName.replace(NOT_NUMBER_REGEX, "")
        originalFiles.add(originalFile)
        originalDirectoryNumber = try {
            dirNumberStr.toInt()
        } catch (e: Exception) {
            print(e)
            0
        }
    }

    fun getKey(): String = originalFiles[0].parent

    private fun getKey(file: File): String {
        return file.parent
    }

    fun addFile(file: File) {
        if (getKey(file) == getKey()) {
            originalFiles.add(file)
        } else {
            throw IllegalArgumentException("Invalid key%s".format(getKey(file)))
        }
    }

    private fun updatedDate(): Calendar = Calendar.getInstance().apply {
        time = Date(originalFiles[0].lastModified())
    }

    fun getReservedCopyToFullPathList(parentDir: Path, index: Int?): List<Path> {
        return Extension.values().flatMap {
            getScheduledCopyToFullPathList(parentDir, index, it)
        }
    }

    fun getScheduledCopyToFullPathList(parentDir: Path, index: Int?, extension: Extension): List<Path> {
        return originalFiles.map {
            val micType = MicType.toMic(it.name)
            if (index == null)
                "%s%s.%s".format(it.name, micType.name.toLowerCase(), extension.name.toLowerCase())
            else
                "%s%s (%d).%s".format(it.name, micType.name.toLowerCase(), index, extension.name.toLowerCase())

        }.map { fileName ->
            parentDir.resolve(fileName)
        }

    }

    /**
     * spec directory name.
     * bar when /foo/bar/baz.wav
     */
    private var _copyToDirectoryName: String? = null

    /**
     * directory name is generate from last updated date if not set.
     * 2019-05-01 /foo/2019-05-01/bar.wav
     */
    var copyToDirectoryName: String
        get() = _copyToDirectoryName ?: updatedDate().directoryNameString()
        set(value) {
            _copyToDirectoryName = value
        }


    companion object {
        private val NOT_NUMBER_REGEX = Regex("[^0-9]")

    }
}
