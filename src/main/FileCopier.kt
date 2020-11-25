package main

import main.fileName.Extension
import main.fileName.FileCopyInfo
import java.io.File
import java.nio.file.Path


class FileCopier {
    /**
     * Make directory and Copy files from H2n to PC
     * @param files copy files
     * @param outputDir destination
     *
     */
    fun copyFiles(files: List<File>, outputDir: File) {
        val fileInfoList = files.map { FileCopyInfo(it) }
        fileInfoList.forEach {
            it.copyToDirectoryName = makeDir(outputDir, it.copyToDirectoryName)
            val copyToPath = getCopyToPath(outputDir.toPath(), it)
            it.originalFile.copyTo(copyToPath.toFile(), overwrite = false)
        }


    }

    private fun isExistFile(parentDir: Path, file: FileCopyInfo, index:Int?): Boolean {
        return Extension.values().firstOrNull {
            file.getScheduledCopyToFullPath(parentDir, index, it).toFile().exists()
        }?.let { true } ?: false
    }

    private fun getCopyToPath(
        parentDir: Path,
        file: FileCopyInfo,
        index: Int? = null
    ): Path {
        val nameWithoutExtensionWithNumber = file.getFileName(index, null)
        return if (isExistFile(parentDir, file, index))
            getCopyToPath(parentDir, file, (index ?: 1) + 1)
        else
            return parentDir.resolve(file.copyToDirectoryName)
                .resolve("%s.%s".format(nameWithoutExtensionWithNumber, Extension.Wav.name.toLowerCase()))
    }


    /**
     * Make directory if it is not existed
     * @param parentDir make directory in to the parentDirD
     * @param dirName name of make directory name
     * @return created(or existed) directory name
     */
    fun makeDir(parentDir: File, dirName: String): String = makeDirIndex(parentDir, dirName, null)

    /**
     * Make directory if it is not existed
     * If a file with the same name as Directory exists, add a number to the Directory name.
     * ex make directory as /foo/bar (1) if foo/bar **FILE** is exist.
     *
     * @param parentDir make directory in to the parentDir
     * ex: make directory /foo/2019-05-01 (1) if set /foo
     * @param dirName name of make directory name
     * ex: make directory /foo/2019-05-01 (1)if set 2019-05-01
     * @param index Should set null
     * this parameter set by only itself
     * @return created(or existed) directory name
     */
    private fun makeDirIndex(parentDir: File, dirName: String, index: Int? = null): String {
        val childDir = if (index == null) dirName else "$dirName ($index)"
        val dir = parentDir.resolve(childDir)
        if (!dir.exists()) {
            dir.mkdirs()
            return childDir
        } else if (dir.isFile) {
            return makeDirIndex(parentDir, dirName, (index ?: 1) + 1)
        }
        return childDir
    }
}