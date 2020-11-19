package test

import main.H2nFilter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File


internal class H2nFilterTest {
    @Test
    fun testFilter() {
        assertTrue(H2nFilter().filter(File("/var/folders/4CH/FOLDER01/SR001MS.WAV")))
        assertTrue(H2nFilter().filter(File("/var/folders/4CH/FOLDER01/SR002MS.WAV")))
        assertTrue(H2nFilter().filter(File("/var/folders/4CH/FOLDER01/SR002XY.WAV")))
        assertTrue(H2nFilter().filter(File("/var/folders/4CH/FOLDER01/SR001MS.WAV")))
        assertTrue(H2nFilter().filter(File("/var/folders/4CH/FOLDER02/SR001MS.WAV")))
        assertTrue(H2nFilter().filter(File("/var/folders/4CH/FOLDER02/SR001XY.WAV")))
        assertFalse(H2nFilter().filter(File("/var/folders/4CH/FOLDER01/RR001XY.WAV")))
        assertFalse(H2nFilter().filter(File("/var/folders/4CH/FOLDER01/RR001MS.WAV")))
        assertFalse(H2nFilter().filter(File("/var/folders/STEREO/FOLDER01/SR001MS.WAV")))
        assertFalse(H2nFilter().filter(File("/var/folders/4CH/foo/SR001MS.WAV")))
        assertFalse(H2nFilter().filter(File("/var/folders/4CH/FOLDER01/SR001MS.TXT")))
        assertFalse(H2nFilter().filter(File("/var/folders/4CH/FOLDER01/SR001MS.MP3")))
    }
}