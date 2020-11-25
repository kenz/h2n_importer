package main.createDirectory

import main.fileName.directoryNameString
import java.io.File
import java.nio.file.Path
import java.util.*

class DirectoryNameConverter {
    fun convertDirectoryName(inputFile: File): String{
        return Calendar.getInstance().apply{
            this.time = Date(inputFile.lastModified())
        }.directoryNameString()
    }
}