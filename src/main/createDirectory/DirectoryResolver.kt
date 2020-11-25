package main.createDirectory

import main.fileSearcher.AudioFileKey
import java.nio.file.Path

class DirectoryResolver {

    fun directoryResolve(path:Path): Path {
        val file = path.toFile()
        if(!file.exists()){
            file.mkdirs()
            return path
        }
        if(file.isDirectory){
            return path
        }
        return getNumberedDirectory(path)
    }
    private fun getNumberedDirectory(path:Path): Path{
        var index = 1
        while(index<Int.MAX_VALUE){
            val checkPath = path.parent.resolve( "%s (%d)".format(path.last().toString() ,index))
            val checkFile = checkPath.toFile()
            if(!checkFile.exists()){
                checkFile.mkdirs()
                return checkPath
            }
            if(checkFile.isDirectory){
                return checkPath
            }
            index++
        }
        throw (FileAlreadyExistsException(path.toFile()))

    }
}