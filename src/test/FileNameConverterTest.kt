package test

import main.fileName.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path
import java.util.*

internal class FileNameConverterTest {
    @Test
    fun testFileName(@TempDir dir: Path) {

        val sauceFiles = createTestSauceData(dir)
        val fileNameConverterMS11 = FileNameConverter(sauceFiles[0])
        val fileNameConverterXY11 = FileNameConverter(sauceFiles[1])
        val fileNameConverterMS21 = FileNameConverter(sauceFiles[2])
        val fileNameConverterXY21 = FileNameConverter(sauceFiles[3])

        assertEquals(fileNameConverterMS11.forceGetValue("mic"), MicType.In)
        assertEquals(fileNameConverterXY11.forceGetValue("mic"), MicType.Out)
        assertEquals(fileNameConverterMS21.forceGetValue("mic"), MicType.In)
        assertEquals(fileNameConverterXY21.forceGetValue("mic"), MicType.Out)

        assertEquals(fileNameConverterMS11.forceGetValue("directoryNumber"), 1)
        assertEquals(fileNameConverterXY11.forceGetValue("directoryNumber"), 1)
        assertEquals(fileNameConverterMS21.forceGetValue("directoryNumber"), 2)
        assertEquals(fileNameConverterXY21.forceGetValue("directoryNumber"), 2)

        assertEquals(fileNameConverterMS11.forceGetValue("nameWithoutExtension"), "SR001MS")
        assertEquals(fileNameConverterXY11.forceGetValue("nameWithoutExtension"), "SR001XY")
        assertEquals(fileNameConverterMS21.forceGetValue("nameWithoutExtension"), "SR001MS")
        assertEquals(fileNameConverterXY21.forceGetValue("nameWithoutExtension"), "SR001XY")
        val ms11Date: Calendar = fileNameConverterMS11.forceGetValue("updatedDate")
        assertEquals(ms11Date.fileNameString(), "20190501_020304")
        assertEquals(fileNameConverterMS11.directoryName, "2019-05-01")
        val xy11Date: Calendar = fileNameConverterXY11.forceGetValue("updatedDate")
        assertEquals(xy11Date.fileNameString(), "20190501_020304")
        assertEquals(fileNameConverterXY11.directoryName, "2019-05-01")
        val ms21Date: Calendar = fileNameConverterMS21.forceGetValue("updatedDate")
        assertEquals(ms21Date.fileNameString(), "20191012_131415")
        assertEquals(fileNameConverterMS21.directoryName, "2019-10-12")
        val xy21Date: Calendar = fileNameConverterXY21.forceGetValue("updatedDate")
        assertEquals(xy21Date.fileNameString(), "20191012_131415")
        assertEquals(fileNameConverterXY21.directoryName, "2019-10-12")
    }

    @Test
    fun testGetPathExistFile(@TempDir dir: Path) {
        // made exist data
        val sauceFiles = createTestSauceData(dir)
        val fileNameConverterMS11 = FileNameConverter(sauceFiles[0])
        val fileNameConverterXY11 = FileNameConverter(sauceFiles[1])
        val fileNameConverterMS21 = FileNameConverter(sauceFiles[2])
        val fileNameConverterXY21 = FileNameConverter(sauceFiles[3])
        val toFolder = dir.resolve("local")
        assertTrue(toFolder.toFile().mkdir())

        val dateFolder = dir.resolve("2019-05-01")
        dateFolder.toFile().mkdir()
        dateFolder.resolve("20190501_020304in.wav").toFile().createNewFile()
        assertEquals(fileNameConverterMS11.getFileName(Extension.Wav),"20190501_020304in (1).wav")
        assertEquals(fileNameConverterXY11.getFileName(Extension.Wav),"20190501_020304out (1).wav")

        assertEquals(fileNameConverterMS21.getFileName(Extension.Wav),"20191012_131415in.wav")
        assertEquals(fileNameConverterXY21.getFileName(Extension.Wav),"20191012_131415out.wav")

    }

    /**
     * Create sauce data
     */
    private fun createTestSauceData(dir: Path):List<File>{
        val fourCh = dir.resolve("4CH")
        assertTrue(fourCh.toFile().mkdir())
        val folder1 = fourCh.resolve("FOLDER01")
        val folder2 = fourCh.resolve("FOLDER02")
        val targetFileMS11 = folder1.resolve("SR001MS.WAV")
        val targetFileXY11 = folder1.resolve("SR001XY.WAV")
        val targetFileMS21 = folder2.resolve("SR001MS.WAV")
        val targetFileXY21 = folder2.resolve("SR001XY.WAV")
        val targetFileMS11File = targetFileMS11.toFile()
        val targetFileXY11File = targetFileXY11.toFile()
        val targetFileMS21File = targetFileMS21.toFile()
        val targetFileXY21File = targetFileXY21.toFile()
        val files = arrayListOf(targetFileMS11File, targetFileXY11File, targetFileMS21File, targetFileXY21File)
        assertTrue(folder1.toFile().mkdir())
        assertTrue(folder2.toFile().mkdir())
        targetFileMS11File.createNewFile()
        targetFileXY11File.createNewFile()
        targetFileMS21File.createNewFile()
        targetFileXY21File.createNewFile()
        targetFileMS11File.setLastModified(Calendar.getInstance().let {
            it.set(2019, 5, 1, 2, 3, 4)
            it.timeInMillis
        })
        targetFileXY11File.setLastModified(Calendar.getInstance().let {
            it.set(2019, 5, 1, 2, 3, 4)
            it.timeInMillis
        })
        targetFileMS21File.setLastModified(Calendar.getInstance().let {
            it.set(2019, 10, 12, 13, 14, 15)
            it.timeInMillis
        })
        targetFileXY21File.setLastModified(Calendar.getInstance().let {
            it.set(2019, 10, 12, 13, 14, 15)
            it.timeInMillis
        })

       return files
    }

}