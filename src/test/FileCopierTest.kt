package test

import main.FileCopier
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

internal class FileCopierTest {
    @Test
    fun testCreateDirExistDirectory(@TempDir dir: Path) {
        val testDir = createTestFile(dir)
        val existFolder = testDir.resolve("test1")
        existFolder.toFile().mkdir()

        // Did not make directory when it is already existed
        val targetFolder = testDir.resolve("test1")
        val target = FileCopier()
        target.makeDir(testDir.toFile(), "test1")
        assertTrue(targetFolder.toFile().exists())
        assertTrue(targetFolder.toFile().isDirectory)

        val numberFolder = testDir.resolve("test1 (1)")
        assertFalse(numberFolder.toFile().exists())
        assertFalse(numberFolder.toFile().isDirectory)
    }

    @Test
    fun testCreateDirNotExistDirectory(@TempDir dir: Path) {
        val testDir = createTestFile(dir)

        // make new directory when it is not existed
        val targetFolder = testDir.resolve("test1")
        val target = FileCopier()
        assertEquals(target.makeDir(testDir.toFile(), "test1"),"test1")
        assertTrue(targetFolder.toFile().exists())
        assertTrue(targetFolder.toFile().isDirectory)

        val numberFolder = testDir.resolve("test1 (1)")
        assertFalse(numberFolder.toFile().exists())
        assertFalse(numberFolder.toFile().isDirectory)
    }

    @Test
    fun testCreateDirExistFile(@TempDir dir: Path) {
        val testDir = createTestFile(dir)
        val existFile = testDir.resolve("test1")
        existFile.toFile().createNewFile()

        // Make directory with number when it is already same name existed
        val targetFolder = testDir.resolve("test1")
        val target = FileCopier()
        assertEquals(target.makeDir(testDir.toFile(), "test1"),"test1 (1)")
        assertTrue(targetFolder.toFile().exists())
        assertTrue(targetFolder.toFile().isFile)

        val numberFolder = testDir.resolve("test1 (1)")
        assertTrue(numberFolder.toFile().exists())
        assertTrue(numberFolder.toFile().isDirectory)
    }
    @Test
    fun testCreateDirExistFile2(@TempDir dir: Path) {
        val testDir = createTestFile(dir)
        val existFile0 = testDir.resolve("test1")
        existFile0.toFile().createNewFile()
        val existFile1 = testDir.resolve("test1 (1)")
        existFile1.toFile().createNewFile()

        // Make directory with number when it is already same name existed
        val targetFolder = testDir.resolve("test1")
        val target = FileCopier()
        assertEquals(target.makeDir(testDir.toFile(), "test1"),"test1 (2)")
        assertTrue(targetFolder.toFile().exists())
        assertTrue(targetFolder.toFile().isFile)

        val numberFolder1 = testDir.resolve("test1 (1)")
        assertTrue(numberFolder1.toFile().exists())
        assertTrue(numberFolder1.toFile().isFile)

        val numberFolder2 = testDir.resolve("test1 (2)")
        assertTrue(numberFolder2.toFile().exists())
        assertTrue(numberFolder2.toFile().isDirectory)
    }


    private fun createTestFile(dir: Path): Path {
        // create /4CH/FOLDER01/
        val fourCh = dir.resolve("4CH")
        val folder1 = fourCh.resolve("FOLDER01")
        assertTrue(File(folder1.toUri()).mkdirs())
        return folder1
    }

}