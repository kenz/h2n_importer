package test

import main.fileName.FileNameConverter
import main.fileName.MicType
import main.fileName.directoryNameString
import main.fileName.fileNameString
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
        cal.set(2019, 5, 1, 2, 3, 4)
        targetFileMS11File.createNewFile()
        assertTrue(targetFileMS11File.exists())
        assertTrue(targetFileMS11File.setLastModified(cal.timeInMillis))
        val fileNameConverterMS11 = FileNameConverter(targetFileMS11File)
        val fileNameConverterXY11 = FileNameConverter(targetFileXY11File)
        val fileNameConverterMS21 = FileNameConverter(targetFileMS21File)
        val fileNameConverterXY21 = FileNameConverter(targetFileXY21File)

        assertEquals(fileNameConverterMS11.mic, MicType.In)
        assertEquals(fileNameConverterXY11.mic, MicType.Out)

        assertEquals(fileNameConverterMS21.mic, MicType.In)
        assertEquals(fileNameConverterXY21.mic, MicType.Out)

        assertEquals(fileNameConverterMS11.folderNumber, 1)
        assertEquals(fileNameConverterXY11.folderNumber, 1)
        assertEquals(fileNameConverterMS21.folderNumber, 2)
        assertEquals(fileNameConverterXY21.folderNumber, 2)

        assertEquals(fileNameConverterMS11.nameWithoutExtension, "SR001MS")
        assertEquals(fileNameConverterXY11.nameWithoutExtension, "SR001XY")
        assertEquals(fileNameConverterMS21.nameWithoutExtension, "SR001MS")
        assertEquals(fileNameConverterXY21.nameWithoutExtension, "SR001XY")
        assertEquals(fileNameConverterMS11.updatedDate.fileNameString(), "20190501_020304")
        assertEquals(fileNameConverterMS11.directoryName, "2019-05-01")


    }
}