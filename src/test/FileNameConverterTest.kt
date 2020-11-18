package test

import main.FileNameConverter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path
import java.util.*

internal class FileNameConverterTest {
    @Test
    fun testFileNameTestInMic(@TempDir dir: Path) {
        val fourCh = dir.resolve("4CH")
        val folder1 = fourCh.resolve("FOLDER01")
        val folder2 = fourCh.resolve("FOLDER02")
        val targetFileMS11 = folder1.resolve("SR001MS.WAV")
        val targetFileXY11 = folder1.resolve("SR001XY.WAV")
        val targetFileMS21 = folder2.resolve("SR001MS.WAV")
        val targetFileXY21 = folder2.resolve("SR001XY.WAV")
        val targetFileMS11File = File(targetFileMS11.toUri())
        val targetFileXY11File = File(targetFileXY11.toUri())
        val targetFileMS21File = File(targetFileMS21.toUri())
        val targetFileXY21File = File(targetFileXY21.toUri())
        assertTrue(targetFileMS11File.mkdirs())

        val cal = Calendar.getInstance()
        cal.set(2019, 5,1, 2,3,4)
        targetFileMS11File.createNewFile()
        assertTrue(targetFileMS11File.exists())
        assertTrue(targetFileMS11File.setLastModified(cal.timeInMillis))
        val fileNameConverterMS11 = FileNameConverter(targetFileMS11File)
        val fileNameConverterXY11 = FileNameConverter(targetFileXY11File)
        val fileNameConverterMS21 = FileNameConverter(targetFileMS21File)
        val fileNameConverterXY21 = FileNameConverter(targetFileXY21File)

        assertTrue(fileNameConverterMS11.inMic)
        assertFalse(fileNameConverterMS11.outMic)
        assertFalse(fileNameConverterXY11.inMic)
        assertTrue(fileNameConverterXY11.outMic)

        assertTrue(fileNameConverterMS21.inMic)
        assertFalse(fileNameConverterMS21.outMic)
        assertFalse(fileNameConverterXY21.inMic)
        assertTrue(fileNameConverterXY21.outMic)

        assertEquals(fileNameConverterMS11.folderNumnber, 1)
        assertEquals(fileNameConverterXY11.folderNumnber, 1)
        assertEquals(fileNameConverterMS21.folderNumnber, 2)
        assertEquals(fileNameConverterXY21.folderNumnber, 2)

        assertEquals(fileNameConverterMS11.originalFileName, "SR001MS.WAV")
        assertEquals(fileNameConverterXY11.originalFileName, "SR001XY.WAV")
        assertEquals(fileNameConverterMS21.originalFileName, "SR001MS.WAV")
        assertEquals(fileNameConverterXY21.originalFileName, "SR001XY.WAV")
        assertEquals(fileNameConverterMS11.year, 2019)
        assertEquals(fileNameConverterMS11.month, 5)
        assertEquals(fileNameConverterMS11.day, 1)
        assertEquals(fileNameConverterMS11.hour, 2)
        assertEquals(fileNameConverterMS11.minute, 3)
        assertEquals(fileNameConverterMS11.second, 4)


    }
}