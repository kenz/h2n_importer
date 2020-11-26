package test.createDirectory

import main.createDirectory.DirectoryNameRule
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path
import java.util.*
import kotlin.test.assertEquals

class DirectoryNameRuleTest {

    @Test
    fun testDirectoryNameRule(@TempDir dir: Path) {
        val fourCh = dir.resolve("4CH")
        val folder1 = fourCh.resolve("FOLDER01")
        folder1.toFile().mkdirs()
        val file = folder1.resolve("SR001MS.WAV").toFile()
        file.createNewFile()
        file.setLastModified(Calendar.getInstance().let {
            it.set(2019, 5, 1, 2, 3, 4)
            it.timeInMillis
        })
        val target = DirectoryNameRule()
        val directoryName = target.convertDirectoryName(file)

        assertEquals(directoryName, "2019-05-01")




    }

}
