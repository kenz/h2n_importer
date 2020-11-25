package main.createDirectory

import main.fileSearcher.AudioFileKey
import java.nio.file.Path

data class DirectoryResolverReport(val sauceFileSet :MutableSet<Path>, val outputDir:Path)