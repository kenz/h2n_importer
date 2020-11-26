package main.fileSearcher
import main.H2nFilter
import java.io.File
import java.nio.file.Path

class FileSearcher {
    fun find(path: Path):Map<AudioFileKey, FileSearcherReport>{
        val h2nFilter= H2nFilter()
        val list = getFiles(path.toFile()).filter{
            h2nFilter.filter(it)
        }

        val result = HashMap<AudioFileKey, FileSearcherReport>()
        list.forEach {
            val key = AudioFileKey.createOf(it.toPath())
            val set = result[key]
            if(set == null){
                result[key] = FileSearcherReport(mutableSetOf(it.toPath()))
            }else{
                set.addPath(it.toPath())
            }
        }

        return result

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