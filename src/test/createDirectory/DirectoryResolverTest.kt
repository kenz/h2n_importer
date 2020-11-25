package test.createDirectory

import main.createDirectory.DirectoryResolver
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import test.forceGetFunction
import java.io.File
import java.nio.file.Path
import kotlin.test.assertEquals

class DirectoryResolverTest {

    @Test
    fun testResolveDirectoryExistDirectory(@TempDir dir: Path) {
        val testDir = createTestFile(dir)
        val existFolder = testDir.resolve("test1")
        existFolder.toFile().mkdir()

        // Did not make directory when it is already existed
        val target = DirectoryResolver()
        val resolvedPath:Path = target.directoryResolve(existFolder)

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
        val resolvedPath:Path = target.directoryResolve(targetFolder)

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

        val resolvedPath:Path = target.directoryResolve(existFolder)

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

        val resolvedPath:Path = target.directoryResolve(existedFile)
        val createdDirectory = testDir.resolve("test (2)")
        assertEquals(resolvedPath, createdDirectory)
        assertTrue(createdDirectory.toFile().exists())
        assertTrue(createdDirectory.toFile().isDirectory)
    }

    private fun createTestFile(dir: Path): Path {
        // create /4CH/FOLDER01/
        val fourCh = dir.resolve("4CH")
        val folder1 = fourCh.resolve("FOLDER01")
        Assertions.assertTrue(File(folder1.toUri()).mkdirs())
        return folder1
    }
}
