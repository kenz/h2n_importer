package main

import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.attribute.FileTime
import java.text.SimpleDateFormat


class FileCopier {
    fun copyFiles(files: List<String>, outputPath: String) {
        val fileInfoList = files.map{
            FileNameConverter(File(it))
        }

    }
    fun convertFileName(original: File){
        val latestFile = original.lastModified()


    }
}