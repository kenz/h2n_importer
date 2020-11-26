package test.createDirectory

import main.createDirectory.DirectoryResolver
import main.fileSearcher.AudioFileKey
import main.fileSearcher.FileSearcher
import main.fileSearcher.FileSearcherReport
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import test.forceGetFunction
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.test.assertEquals

class DirectoryResolverTest {

    @Test
    fun testDirectoryCreateMSOnly(@TempDir dir:Path){
        val fourCh = dir.resolve("4CH")
        val local = dir.resolve("local")
        local.toFile().mkdirs()
        val folder1 = fourCh.resolve("FOLDER01")
        Files.createDirectory(fourCh)
        Files.createDirectory(folder1)
        val targetMS11Path = folder1.resolve("SR001MS.WAV")
        val targetMS11File = targetMS11Path.toFile()
        targetMS11File.createNewFile()
        targetMS11File.setLastModified(Calendar.getInstance().let {
            it.set(2019, 5, 1, 2, 3, 4)
            it.timeInMillis
        })
        val key = AudioFileKey(folder1, "SR001")
        val fileSet11 = mutableSetOf(targetMS11Path)
        val searcherReport = FileSearcherReport(fileSet11)
        val target = DirectoryResolver()
        val resultReport = target.directoryCreate(outputParentPath = local, key = key, fileSearcherReport = searcherReport )
        val outputDayDir = local.resolve("2019-05-01").toFile()
        assertTrue(outputDayDir.exists())
        assertTrue(outputDayDir.isDirectory)
        assertEquals(resultReport.outputDir, outputDayDir.toPath())
        assertEquals(resultReport.sauceFiles,fileSet11)
    }
    @Test
    fun testDirectoryCreateMSXY(@TempDir dir:Path){
        val fourCh = dir.resolve("4CH")
        val local = dir.resolve("local")
        local.toFile().mkdirs()
        val folder1 = fourCh.resolve("FOLDER01")
        Files.createDirectory(fourCh)
        Files.createDirectory(folder1)
        val targetMS11Path = folder1.resolve("SR001MS.WAV")
        val targetMS11File = targetMS11Path.toFile()
        val targetXY11Path = folder1.resolve("SR001XY.WAV")
        val targetXY11File = targetXY11Path.toFile()
        targetMS11File.createNewFile()
        targetMS11File.setLastModified(Calendar.getInstance().let {
            it.set(2019, 5, 1, 2, 3, 4)
            it.timeInMillis
        })
        targetXY11File.createNewFile()
        targetXY11File.setLastModified(Calendar.getInstance().let {
            it.set(2019, 5, 1, 2, 3, 4)
            it.timeInMillis
        })
        val key = AudioFileKey(folder1, "SR001")
        val fileSet11 = mutableSetOf(targetMS11Path, targetXY11Path)
        val searcherReport = FileSearcherReport(fileSet11)
        val target = DirectoryResolver()
        val resultReport = target.directoryCreate(outputParentPath = local, key = key, fileSearcherReport = searcherReport )
        val outputDayDir = local.resolve("2019-05-01").toFile()
        assertTrue(outputDayDir.exists())
        assertTrue(outputDayDir.isDirectory)
        assertEquals(resultReport.outputDir, outputDayDir.toPath())
        assertEquals(resultReport.sauceFiles,fileSet11)
    }


    @Test
    fun testResolveDirectoryExistDirectory(@TempDir dir: Path) {
        val testDir = createTestFile(dir)
        val existFolder = testDir.resolve("test1")
        existFolder.toFile().mkdir()

        // Did not make directory when it is already existed
        val target = DirectoryResolver()
        val resolvedPath:Path =target.forceGetFunction("directoryResolve", Path::class.java).invoke(existFolder)

        assertEquals(resolvedPath, existFolder)
        assertTrue(resolvedPath.toFile().exists())
        assertTrue(resolvedPath.toFile().isDirectory)
    }

    @Test
    fun testResolveDirectoryNotExistDirectory(@TempDir dir: Path) {
        val testDir = createTestFile(dir)
        val targetFolder = testDir.resolve("test")

        //  make directory when directory is nothing
        val target = DirectoryResolver()
        val resolvedPath:Path =target.forceGetFunction("directoryResolve", Path::class.java).invoke(targetFolder)

        assertEquals(resolvedPath, targetFolder)
        assertTrue(resolvedPath.toFile().exists())
        assertTrue(resolvedPath.toFile().isDirectory)
    }

    @Test
    fun testResolveDirectoryExistFile(@TempDir dir: Path) {
        val testDir = createTestFile(dir)
        val existFolder = testDir.resolve("test")
        existFolder.toFile().createNewFile()

        // Make new directory when already existed FILE
        val target = DirectoryResolver()

        val resolvedPath:Path =target.forceGetFunction("directoryResolve", Path::class.java).invoke(existFolder)

        val createdDirectory = testDir.resolve("test (1)")
        assertEquals(resolvedPath, createdDirectory)
        assertTrue(createdDirectory.toFile().exists())
        assertTrue(createdDirectory.toFile().isDirectory)
    }
    @Test
    fun testResolveDirectoryExistFile2(@TempDir dir: Path) {
        val testDir = createTestFile(dir)
        val existedFile= testDir.resolve("test")
        existedFile.toFile().createNewFile()

        val existedFile1= testDir.resolve("test (1)")
        existedFile1.toFile().createNewFile()
        // Make new directory when already existed FILE
        val target = DirectoryResolver()

        val resolvedPath:Path =target.forceGetFunction("directoryResolve", Path::class.java).invoke(existedFile)
        val createdDirectory = testDir.resolve("test (2)")
        assertEquals(resolvedPath, createdDirectory)
        assertTrue(createdDirectory.toFile().exists())
        assertTrue(createdDirectory.toFile().isDirectory)
    }

    private fun createTestFile(dir: Path): Path {
        // create /4CH/FOLDER01/
        val fourCh = dir.resolve("4CH")
        val folder1 = fourCh.resolve("FOLDER01")
        assertTrue(File(folder1.toUri()).mkdirs())
        return folder1
    }
}
