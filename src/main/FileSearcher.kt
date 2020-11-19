package main
import java.io.File

class FileSearcher {
    fun find(path: File):List<File>{
        val h2nFilter= H2nFilter()
        return getFiles(path).filter{
            h2nFilter.filter(it)
        }.sorted()
    }

    private fun getFiles(file:File):List<File>{
        val fileList = ArrayList<File>()
        if(file.isFile){
            fileList.add(file)
        }
        if(file.isDirectory){
            file.listFiles()?.forEach{
                fileList.addAll(getFiles(it))
            }
        }
        return fileList
    }
}