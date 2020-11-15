import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Files
import java.nio.file.Path

internal class FileSearcherTest {

    @Test
    fun testFindFiles(@TempDir dir: Path) {
        val directory = dir.resolve("4CH")
        Files.createDirectory(directory)
        val targetFileMS1 = directory.resolve("SR001MS.WAV")
        val targetFileXY1 = directory.resolve("SR001XY.WAV")
        val targetFileMS2 = directory.resolve("SR002MS.WAV")
        val targetFileXY2 = directory.resolve("SR002XY.WAV")
        Files.createFile(targetFileMS1)
        Files.createFile(targetFileXY1)
        Files.createFile(targetFileMS2)
        Files.createFile(targetFileXY2)
        val ignoreFileMS1 = directory.resolve("RR001MS.WAV")
        val ignoreFileXY1 = directory.resolve("RR001XY.WAV")
        Files.createFile(ignoreFileMS1)
        Files.createFile(ignoreFileXY1)
        val actual = ArrayList<String>()
        actual.add(targetFileMS1.toString())
        actual.add(targetFileXY1.toString())
        actual.add(targetFileMS2.toString())
        actual.add(targetFileXY2.toString())

        val fileSearcher = FileSearcher()
        assertIterableEquals(fileSearcher.find(dir), actual);
    }
}