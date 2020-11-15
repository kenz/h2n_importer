package test

import main.H2nFilter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class H2nFilterTest {
    @Test
    fun testFilter() {
        assertTrue(H2nFilter().filter("/var/folders/4CH/folder1/SR001MS.WAV"))
        assertTrue(H2nFilter().filter("/var/folders/4CH/folder1/SR002MS.WAV"))
        assertTrue(H2nFilter().filter("/var/folders/4CH/folder1/SR002XY.WAV"))
        assertTrue(H2nFilter().filter("/var/folders/4CH/folder1/SR001MS.WAV"))
        assertTrue(H2nFilter().filter("/var/folders/4CH/folder2/SR001MS.WAV"))
        assertTrue(H2nFilter().filter("/var/folders/4CH/folder2/SR001XY.WAV"))
        assertFalse(H2nFilter().filter("/var/folders/4CH/folder1/RR001XY.WAV"))
        assertFalse(H2nFilter().filter("/var/folders/4CH/folder1/RR001MS.WAV"))
        assertFalse(H2nFilter().filter("/var/folders/STEREO/folder1/SR001MS.WAV"))
        assertFalse(H2nFilter().filter("/var/folders/4CH/foo/SR001MS.WAV"))
        assertFalse(H2nFilter().filter("/var/folders/4CH/folder1/SR001MS.TXT"))
        assertFalse(H2nFilter().filter("/var/folders/4CH/folder1/SR001MS.MP3"))
    }
}