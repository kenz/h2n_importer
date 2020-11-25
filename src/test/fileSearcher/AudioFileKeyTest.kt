package test.fileSearcher

import main.fileSearcher.AudioFileKey
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AudioFileKeyTest {

    @Test
    fun testGetBaseName(){
        Assertions.assertEquals(AudioFileKey.getBaseName("SR001MS.WAV"), "SR001")
        Assertions.assertEquals(AudioFileKey.getBaseName("SR001XY.WAV"), "SR001")
        Assertions.assertEquals(AudioFileKey.getBaseName("SR002MS.WAV"), "SR002")
        Assertions.assertEquals(AudioFileKey.getBaseName("SR002XY.WAV"), "SR002")
    }
}