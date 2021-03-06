package main
import main.fileSearcher.FileSearcher
import java.io.File

fun main(args: Array<String>) {
    val outputPath = File(getArgs(args, 0,  "./" ))
    val inputPath = File(getArgs(args, 1,  "/Volumes/H2n_sd" ))
    val files = FileSearcher().find(inputPath.toPath())


}

fun <T> getArgs(args: Array<T>, index: Int, default: T): T = if (args.size > index) args[index] else default