package main.fileName

enum class MicType {
    In, Out, UnKnown;

    fun fileNameString(): String = when (this) {
        UnKnown -> ""
        else -> name.toLowerCase()
    }

    companion object {
        private val MS_REGEX = Regex(".*MS\\.WAV")
        private val XY_REGEX = Regex(".*XY\\.WAV")
        fun toMic(fileName:String): MicType = when{
            MS_REGEX.matches(fileName) -> In
            XY_REGEX.matches(fileName) -> Out
            else -> UnKnown
        }
    }
}