package main.createDirectory

import java.io.File
import java.util.Calendar
import java.util.Date

class DirectoryNameRule {
    fun convertDirectoryName(file: File):String = directoryNameString(file.updatedDateCalendar())

    private fun File.updatedDateCalendar(): Calendar = Calendar.getInstance().apply {
        time = Date(lastModified())
    }
}

fun directoryNameString(calendar:Calendar): String = "%04d-%02d-%02d".format(
    calendar.get(Calendar.YEAR),
    calendar.get(Calendar.MONTH),
    calendar.get(Calendar.DAY_OF_MONTH),
)

fun Calendar.fileNameString(): String = "%04d%02d%02d_%02d%02d%02d".format(
    get(Calendar.YEAR),
    get(Calendar.MONTH),
    get(Calendar.DAY_OF_MONTH),
    get(Calendar.HOUR_OF_DAY),
    get(Calendar.MINUTE),
    get(Calendar.SECOND)
)
