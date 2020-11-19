package main

import java.io.File


class FileCopier {
    fun copyFiles(files: List<String>, outputDir: File) {
        val fileInfoList = files.map {
            FileNameConverter(File(it))
        }
        fileInfoList.forEach {
            it.directoryName = makeDir(outputDir, it.directoryName)
        }

    }
    /**
     * Make directory if it is not existed
     * @param parentDir make directory in to the parentDirD
     * @param dirName name of make directory name
     * @return created(or existed) directory name
     */
    fun makeDir(parentDir: File, dirName: String): String =makeDirIndex(parentDir, dirName, null)

    /**
     * Make directory if it is not existed
     * If a file with the same name as Directory exists, add a number to the Directory name.
     * @param parentDir make directory in to the parentDirD
     * @param dirName name of make directory name
     * @param index Should set null
     * @return created(or existed) directory name
     */
    private fun makeDirIndex(parentDir: File, dirName:String, index:Int? = null):String{
        val childDir = if (index == null) dirName else "$dirName ($index)"
        val dir = parentDir.resolve(childDir)
        if (!dir.exists()) {
            dir.mkdir()
            return childDir
        } else if (dir.isFile) {
            return makeDirIndex(parentDir, dirName, (index ?: 0) + 1)
        }
        return childDir
    }
}