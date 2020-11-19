package main

import java.io.File

class H2nFilter {

    fun filter(file: File):Boolean = REGEX.containsMatchIn(file.path)

    companion object{
        private val REGEX = Regex("""4CH\/FOLDER\d{2}+/SR\d{3}+(MS|XY)\.WAV$""")
    }

}