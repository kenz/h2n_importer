package main

class H2nFilter {

    fun filter(path: String):Boolean = REGEX.containsMatchIn(path)

    companion object{
        private val REGEX = Regex("""4CH\/FOLDER\d{2}+/SR\d{3}+(MS|XY)\.WAV$""")
    }

}