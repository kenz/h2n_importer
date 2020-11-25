package test

import main.fileName.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import java.util.*

internal class FileNameConverterTest {
    @Test
    fun testFileName(@TempDir dir: Path) {

        val sauceFiles = createTestSauceData(dir)
        val fileNameConverterMS11 = FileCopyInfo(sauceFiles[0])
        val fileNameConverterXY11 = FileCopyInfo(sauceFiles[1])
        val fileNameConverterMS21 = FileCopyInfo(sauceFiles[2])
        val fileNameConverterXY21 = FileCopyInfo(sauceFiles[3])

        assertEquals(fileNameConverterMS11.forceGetValue("mic"), MicType.In)
        assertEquals(fileNameConverterXY11.forceGetValue("mic"), MicType.Out)
        assertEquals(fileNameConverterMS21.forceGetValue("mic"), MicType.In)
        assertEquals(fileNameConverterXY21.forceGetValue("mic"), MicType.Out)

        assertEquals(fileNameConverterMS11.forceGetValue("originalDirectoryNumber"), 1)
        assertEquals(fileNameConverterXY11.forceGetValue("originalDirectoryNumber"), 1)
        assertEquals(fileNameConverterMS21.forceGetValue("originalDirectoryNumber"), 2)
        assertEquals(fileNameConverterXY21.forceGetValue("originalDirectoryNumber"), 2)

        val ms11Date: Calendar = fileNameConverterMS11.forceGetValue("updatedDate")
        assertEquals(ms11Date.fileNameString(), "20190501_020304")
        assertEquals(fileNameConverterMS11.copyToDirectoryName, "2019-05-01")
        val xy11Date: Calendar = fileNameConverterXY11.forceGetValue("updatedDate")
        assertEquals(xy11Date.fileNameString(), "20190501_020304")
        assertEquals(fileNameConverterXY11.copyToDirectoryName, "2019-05-01")
        val ms21Date: Calendar = fileNameConverterMS21.forceGetValue("updatedDate")
        assertEquals(ms21Date.fileNameString(), "20191012_131415")
        assertEquals(fileNameConverterMS21.copyToDirectoryName, "2019-10-12")
        val xy21Date: Calendar = fileNameConverterXY21.forceGetValue("updatedDate")
        assertEquals(xy21Date.fileNameString(), "20191012_131415")
        assertEquals(fileNameConverterXY21.copyToDirectoryName, "2019-10-12")
    }



}