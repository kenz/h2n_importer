package main

import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class FileNameConverter(file: File) {
    val lastUpdatedDateTime: String
    val lastUpdatedDate: String
    val originalFileName: String
    val folderNumnber: Int
    val inMic: Boolean
    val outMic: Boolean
    val year:Int
    val month:Int
    val day:Int

    val hour:Int
    val minute:Int
    val second:Int
    init {
        year = 0
        month = 0
        day= 0
        hour = 0
        minute = 0
        second = 0
        folderNumnber = 0
        originalFileName = file.name
        val cal = Calendar.getInstance()
        cal.time = Date(file.lastModified())
        lastUpdatedDateTime = DATE_TIME_SDF.format(file.lastModified())
        lastUpdatedDate = DATE_SDF.format(file.lastModified())
        inMic = MS_REGEX.matches(originalFileName)
        outMic = XY_REGEX.matches(originalFileName)

    }


    companion object {
        private val DATE_TIME_SDF = SimpleDateFormat("yyyyMMdd_HHmmss")
        private val DATE_SDF = SimpleDateFormat("yyyyMMdd")
        private val MS_REGEX = Regex(".*MS\\.WAV")
        private val XY_REGEX = Regex(".*XY\\.WAV")

    }
}
