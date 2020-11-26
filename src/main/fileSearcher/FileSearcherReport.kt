package main.fileSearcher

import java.nio.file.Path

data class FileSearcherReport(val value: HashMap<AudioFileKey, out Set<Path>>)