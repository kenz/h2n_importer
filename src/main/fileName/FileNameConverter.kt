package main.fileName

import java.io.File
import java.nio.file.Path
import java.util.*

class FileNameConverter(val file: File) {
    /**
     * bar when /foo/bar.wav
     */
    val nameWithoutExtension: String = file.nameWithoutExtension

    /**
     * 2 when FOLDER02
     */
    private val directoryNumber: Int

    /**
     * if use Zoom H2n with extra Mic
     * MS is mean In Mic
     * XY is mean Out Mic
     * todo: this behavior to option.
     */
    private val mic: MicType = MicType.toMic(file.name)

    /**
     * Use last updated date because Zoom H2n set broken created date.
     */
    private val updatedDate: Calendar = Calendar.getInstance().apply {
        time = Date(file.lastModified())
    }

    /**
     * spec directory name.
     * bar when /foo/bar/baz.wav
     */
    private var _directoryName: String? = null

    /**
     * directory name is generate from last updated date if not set.
     * 2019-05-01 /foo/2019-05-01/bar.wav
     */
    var directoryName: String
        get() = _directoryName ?: updatedDate.directoryNameString()
        set(value) {
            _directoryName = value
        }

    init {
        val path = file.path.split("/")
        val dirName = path[path.size - 2]
        val dirNumberStr = dirName.replace(NOT_NUMBER_REGEX, "")
        directoryNumber = try {
            dirNumberStr.toInt()
        } catch (e: Exception) {
            print(e)
            0
        }
    }

    fun getFullPath(parentDir: Path, extension: Extension): Path =
        parentDir.resolve(directoryName).resolve(getFileName(extension))


    fun getFileName(extension: Extension?): String {
        return if (extension == null)
            "%s%s".format(
                updatedDate.fileNameString(),
                mic.fileNameString()
            )
        else
            "%s%s.%s".format(
                updatedDate.fileNameString(),
                mic.fileNameString(),
                extension.name.toLowerCase()
            )
    }

    companion object {
        private val NOT_NUMBER_REGEX = Regex("[^0-9]")
    }
}
