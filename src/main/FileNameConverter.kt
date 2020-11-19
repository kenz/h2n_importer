package main

import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class FileNameConverter(file: File) {
    val nameWithoutExtension: String
    val folderNumber: Int
    val inMic: Boolean
    val outMic: Boolean
    val year: Int
    val month: Int
    val day: Int

    val hour: Int
    val minute: Int
    val second: Int
    private var _directoryName: String? = null
    var directoryName: String
        get() = _directoryName ?: "%04d%02d%02d".format(year, month, day)
        set(value) {
            _directoryName = value
        }

    init {
        nameWithoutExtension = file.nameWithoutExtension
        val cal = Calendar.getInstance()
        cal.time = Date(file.lastModified())
        inMic = MS_REGEX.matches(file.name)
        outMic = XY_REGEX.matches(file.name)
        year = cal.get(Calendar.YEAR)
        month = cal.get(Calendar.MONTH)
        day = cal.get(Calendar.DAY_OF_MONTH)
        hour = cal.get(Calendar.HOUR_OF_DAY)
        minute = cal.get(Calendar.MINUTE)
        second = cal.get(Calendar.SECOND)
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

    companion object {
        private val DATE_TIME_SDF = SimpleDateFormat("yyyyMMdd_HHmmss")
        private val DATE_SDF = SimpleDateFormat("yyyyMMdd")
        private val MS_REGEX = Regex(".*MS\\.WAV")
        private val XY_REGEX = Regex(".*XY\\.WAV")
        private val NOT_NUMBER_REGEX = Regex("[^0-9]")

    }
}
