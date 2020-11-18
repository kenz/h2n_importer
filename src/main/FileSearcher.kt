import main.H2nFilter
import java.io.File

class FileSearcher {
    fun find(path: File):List<String>{
        val h2nFilter= H2nFilter()
        return getFiles(path).filter{
            h2nFilter.filter(it)
        }.sorted()
    }

    private fun getFiles(file:File):List<String>{
        val fileList = ArrayList<String>()
        if(file.isFile){
            fileList.add((file.path))
        }
        if(file.isDirectory){
            file.listFiles()?.forEach{
                fileList.addAll(getFiles(it))
            }
        }
        return fileList
    }
}