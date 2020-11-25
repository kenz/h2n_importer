package main.fileName

import java.io.File
import java.nio.file.Path
import java.util.*

class FileCopyInfo(val originalFile: File) {

    /**
     * 2 when FOLDER02
     */
    private val originalDirectoryNumber: Int

    /**
     * if use Zoom H2n with extra Mic
     * MS is mean In Mic
     * XY is mean Out Mic
     * todo: this behavior to option.
     */
    private val mic: MicType = MicType.toMic(originalFile.name)

    /**
     * Use last updated date because Zoom H2n set broken created date.
     */
    private val updatedDate: Calendar = Calendar.getInstance().apply {
        time = Date(originalFile.lastModified())
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
        get() = _copyToDirectoryName ?: updatedDate.directoryNameString()
        set(value) {
            _copyToDirectoryName = value
        }

    init {
        val path = originalFile.path.split("/")
        val dirName = path[path.size - 2]
        val dirNumberStr = dirName.replace(NOT_NUMBER_REGEX, "")
        originalDirectoryNumber = try {
            dirNumberStr.toInt()
        } catch (e: Exception) {
            print(e)
            0
        }
    }

    fun getScheduledCopyToFullPath(parentDir: Path, index: Int?, extension: Extension): Path =
        parentDir.resolve(copyToDirectoryName).resolve(getFileName(index, extension))

    var actualCopyToFullPath: Path? = null

    fun getFileName(index: Int?, extension: Extension?): String {
        val baseFileName = "%s%s".format(updatedDate.fileNameString(), mic.fileNameString())

        val fileName = if (index == null) baseFileName
        else "%s%s (%d)".format(updatedDate.fileNameString(), mic.fileNameString(), index)

        return if (extension == null) fileName
        else "%s.%s".format(fileName, extension.name.toLowerCase())
    }

    companion object {
        private val NOT_NUMBER_REGEX = Regex("[^0-9]")
    }
}
