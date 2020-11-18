import main.FileCopier
import java.io.File
import java.nio.file.Path
import java.text.SimpleDateFormat

fun main(args: Array<String>) {
    val outputPath = getArgs(args, 0,  "./" )
    val inputPath = getArgs(args, 1,  "/Volumes/H2n_sd" )
    val files = FileSearcher().find(File(inputPath))
    FileCopier().copyFiles(files, outputPath)


}

fun <T> getArgs(args: Array<T>, index: Int, default: T): T = if (args.size > index) args[index] else default