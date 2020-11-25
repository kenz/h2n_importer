package main.fileName

import java.io.File
import java.nio.file.Path

class CopyFileInfo(val sauceFile:File){
    var toPath: Path? = null
    val micType: MicType = MicType.toMic(sauceFile.name)
}