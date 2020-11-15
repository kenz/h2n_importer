package main

class H2nFilter {

    fun filter(path: String):Boolean = REGEX.containsMatchIn(path)

    companion object{
        private val REGEX = Regex("""4CH\/folder\d+/SR\d{3}+(MS|XY)\.WAV$""")
    }

}