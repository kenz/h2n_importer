package main.createDirectory

import main.fileSearcher.AudioFileKey
import main.fileSearcher.FileSearcherReport
import java.nio.file.Path
import kotlin.io.path.getLastModifiedTime

class DirectoryResolver {

    fun directoryCreate( outputParentPath: Path, key: AudioFileKey, fileSearcherReport: FileSearcherReport): DirectoryResolverReport {
        val outputDirectory = getOutputDirectory(outputParentPath, fileSearcherReport)
        val resolvedOutputDirectory = directoryResolve(outputDirectory)
        return DirectoryResolverReport(fileSearcherReport.files, resolvedOutputDirectory)
    }

    private fun getOutputDirectory(outputParentPath: Path, fileSearcherReport: FileSearcherReport): Path {
        val path = fileSearcherReport.files.first()
        val directoryName = DirectoryNameRule().convertDirectoryName(path.toFile())
        return outputParentPath.resolve(directoryName)
    }

    private fun directoryResolve(path: Path): Path {
        val file = path.toFile()
        if (!file.exists()) {
            file.mkdirs()
            return path
        }
        if (file.isDirectory) {
            return path
        }
        return getNumberedDirectory(path)
    }

    private fun getNumberedDirectory(path: Path): Path {
        var index = 1
        while (index < Int.MAX_VALUE) {
            val checkPath = path.parent.resolve("%s (%d)".format(path.last().toString(), index))
            val checkFile = checkPath.toFile()
            if (!checkFile.exists()) {
                checkFile.mkdirs()
                return checkPath
            }
            if (checkFile.isDirectory) {
                return checkPath
            }
            index++
        }
        throw (FileAlreadyExistsException(path.toFile()))

    }
}