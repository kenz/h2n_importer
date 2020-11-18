package main

import java.io.File
import java.text.SimpleDateFormat

class FileNameConverter(file: File) {
    val lastUpdatedDateTime: String
    val lastUpdatedDate: String
    val originalFileName: String
    val inMic: Boolean
    val outMic: Boolean

    init {
        originalFileName = file.name
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
