package main.fileCopy

import main.fileSearcher.AudioFileKey
import java.nio.file.Path

data class CopyFileOrder(val key: AudioFileKey, val originalFileSet:Set<Path>, var targetDir:Path)