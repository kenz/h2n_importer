package main.fileName

import java.util.*

fun Calendar.directoryNameString(): String = "%04d-%02d-%02d".format(
    get(Calendar.YEAR),
    get(Calendar.MONTH),
    get(Calendar.DAY_OF_MONTH),
)

fun Calendar.fileNameString(): String = "%04d%02d%02d_%02d%02d%02d".format(
    get(Calendar.YEAR),
    get(Calendar.MONTH),
    get(Calendar.DAY_OF_MONTH),
    get(Calendar.HOUR_OF_DAY),
    get(Calendar.MINUTE),
    get(Calendar.SECOND)
)

