package main.fileSearcher

import java.nio.file.Path

data class FileSearcherReport(val files: MutableSet<Path>){
    fun addPath(path:Path){
        files.add(path)
    }

}