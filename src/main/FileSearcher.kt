import main.H2nFilter
import java.io.File
import java.nio.file.Path

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FileSearcher {
    fun find(path: Path):List<String>{
        val h2nFilter= H2nFilter()
        return getFiles(path.toFile()).filter{
            h2nFilter.filter(it)
        }.sorted()
    }

    private fun getFiles(file:File):List<String>{
        val fileList = ArrayList<String>()
        if(file.isFile){
            fileList.add((file.path))
        }
        if(file.isDirectory){
            file.listFiles().forEach{
                fileList.addAll(getFiles(it))
            }
        }
        return fileList
    }
}