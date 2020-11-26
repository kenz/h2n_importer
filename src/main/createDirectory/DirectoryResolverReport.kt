package main.createDirectory

import java.nio.file.Path

data class DirectoryResolverReport(val sauceFiles:Set<Path>, val outputDir:Path)