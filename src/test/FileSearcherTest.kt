package test

import main.FileSearcher
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

internal class FileSearcherTest {

    @Test
    fun testFindFiles(@TempDir dir: Path) {
        val fourCh = dir.resolve("4CH")
        val folder1 = fourCh.resolve("FOLDER01")
        val folder2 = fourCh.resolve("FOLDER02")
        Files.createDirectory(fourCh)
        Files.createDirectory(folder1)
        Files.createDirectory(folder2)
        val targetFileMS11 = folder1.resolve("SR001MS.WAV")
        val targetFileXY11 = folder1.resolve("SR001XY.WAV")
        val targetFileMS12 = folder1.resolve("SR002MS.WAV")
        val targetFileXY12 = folder1.resolve("SR002XY.WAV")
        val targetFileMS21 = folder2.resolve("SR001MS.WAV")
        val targetFileXY21 = folder2.resolve("SR001XY.WAV")
        Files.createFile(targetFileMS11)
        Files.createFile(targetFileXY11)
        Files.createFile(targetFileMS12)
        Files.createFile(targetFileXY12)
        Files.createFile(targetFileMS21)
        Files.createFile(targetFileXY21)
        val ignoreFileMS1 = folder1.resolve("RR001MS.WAV")
        val ignoreFileXY1 = folder1.resolve("RR001XY.WAV")
        Files.createFile(ignoreFileMS1)
        Files.createFile(ignoreFileXY1)
        val actual = ArrayList<File>()
        actual.add(targetFileMS11.toFile())
        actual.add(targetFileXY11.toFile())
        actual.add(targetFileMS12.toFile())
        actual.add(targetFileXY12.toFile())
        actual.add(targetFileMS21.toFile())
        actual.add(targetFileXY21.toFile())

        val fileSearcher = FileSearcher()
        assertIterableEquals(fileSearcher.find(dir.toFile()), actual);
    }
}