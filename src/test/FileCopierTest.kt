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

    @Test
    fun testCopyDefault(@TempDir dir: Path){
        val local = dir.resolve("local")
        val target=FileCopier()

        val sauceFiles = createTestSauceData(dir)
        target.copyFiles(sauceFiles, local.toFile())
        val toFolder1 = local.resolve("2019-05-01")
        val toFolder2 = local.resolve("2019-10-12")
        val toFileIn1 = toFolder1.resolve("20190501_020304in.wav")
        val toFileOut1 = toFolder1.resolve("20190501_020304out.wav")
        val toFileIn2 = toFolder1.resolve("20191012_131415in.wav")
        val toFileOut2 = toFolder1.resolve("20191012_131415out.wav")
        assertTrue(toFolder1.toFile().exists())
        assertTrue(toFolder2.toFile().exists())
        assertTrue(toFolder1.toFile().isDirectory)
        assertTrue(toFolder2.toFile().isDirectory)
        assertTrue(toFileIn1.toFile().exists())
        assertTrue(toFileOut1.toFile().exists())
        assertTrue(toFileIn2.toFile().isFile)
        assertTrue(toFileOut2.toFile().isFile)
    }

    @Test
    fun testCopyExistDirectory(@TempDir dir: Path){
        val local = dir.resolve("local")
        val target=FileCopier()

        val sauceFiles = createTestSauceData(dir)
        val toFolder1 = local.resolve("2019-05-01")
        val existFile = local.resolve("2019-10-12")
        val toFolder2 = local.resolve("2019-10-12 (1)")
        toFolder1.toFile().mkdirs()
        existFile.toFile().createNewFile()
        val toFileIn1 = toFolder1.resolve("20190501_020304in.wav")
        val toFileOut1 = toFolder1.resolve("20190501_020304out.wav")
        val toFileIn2 = toFolder1.resolve("20191012_131415in.wav")
        val toFileOut2 = toFolder1.resolve("20191012_131415out.wav")

        target.copyFiles(sauceFiles, local.toFile())
        assertTrue(toFolder1.toFile().exists())
        assertTrue(toFolder2.toFile().exists())
        assertTrue(toFolder1.toFile().isDirectory)
        assertTrue(toFolder2.toFile().isDirectory)

        assertTrue(toFileIn1.toFile().exists())
        assertTrue(toFileOut1.toFile().exists())
        assertTrue(toFileIn2.toFile().isFile)
        assertTrue(toFileOut2.toFile().isFile)
    }

    @Test
    fun testCopyExistDirectory2(@TempDir dir: Path){
        val local = dir.resolve("local")
        val target=FileCopier()

        val sauceFiles = createTestSauceData(dir)
        val toFolder1 = local.resolve("2019-05-01")
        val existFile1 = local.resolve("2019-10-12")
        val existFile2 = local.resolve("2019-10-12 (1)")
        val toFolder2 = local.resolve("2019-10-12 (2)")
        toFolder1.toFile().mkdirs()
        existFile1.toFile().createNewFile()
        existFile2.toFile().createNewFile()
        val toFileIn1 = toFolder1.resolve("20190501_020304in.wav")
        val toFileOut1 = toFolder1.resolve("20190501_020304out.wav")
        val toFileIn2 = toFolder2.resolve("20191012_131415in.wav")
        val toFileOut2 = toFolder2.resolve("20191012_131415out.wav")

        target.copyFiles(sauceFiles, local.toFile())

        assertTrue(toFolder1.toFile().exists())
        assertTrue(toFolder2.toFile().exists())
        assertTrue(toFolder1.toFile().isDirectory)
        assertTrue(toFolder2.toFile().isDirectory)
        assertTrue(toFileIn1.toFile().exists())
        assertTrue(toFileOut1.toFile().exists())
        assertTrue(toFileIn2.toFile().exists())
        assertTrue(toFileOut2.toFile().exists())
        assertTrue(toFileIn2.toFile().isFile)
        assertTrue(toFileOut2.toFile().isFile)
    }

    @Test
    fun testCopyExistFile(@TempDir dir: Path){
        val local = dir.resolve("local")
        val target=FileCopier()

        val sauceFiles = createTestSauceData(dir)
        val toFolder1 = local.resolve("2019-05-01")
        val toFolder2 = local.resolve("2019-10-12")
        val existWavFile = toFolder1.resolve("20190501_020304in.wav")
        val existAacFile = toFolder2.resolve("20191012_131415out.aac")
        toFolder1.toFile().mkdirs()
        toFolder2.toFile().mkdirs()
        existWavFile.toFile().createNewFile()
        existAacFile.toFile().createNewFile()

        val toFileIn1 = toFolder1.resolve("20190501_020304in (2).wav")
        val toFileOut1 = toFolder1.resolve("20190501_020304out (2).wav")
        val toFileIn2 = toFolder2.resolve("20191012_131415in (2).wav")
        val toFileOut2 = toFolder2.resolve("20191012_131415out (2).wav")

        assertTrue(toFolder1.toFile().exists())
        assertTrue(toFolder2.toFile().exists())
        assertTrue(toFolder1.toFile().isDirectory)
        assertTrue(toFolder2.toFile().isDirectory)
        target.copyFiles(sauceFiles, local.toFile())
        assertTrue(toFileIn1.toFile().exists())
        assertTrue(toFileOut1.toFile().exists())
        assertTrue(toFileIn2.toFile().isFile)
        assertTrue(toFileOut2.toFile().isFile)
    }

    private fun createTestFile(dir: Path): Path {
        // create /4CH/FOLDER01/
        val fourCh = dir.resolve("4CH")
        val folder1 = fourCh.resolve("FOLDER01")
        assertTrue(File(folder1.toUri()).mkdirs())
        return folder1
    }

}