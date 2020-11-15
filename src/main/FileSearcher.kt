import java.io.File
import java.nio.file.Path

class FileSearcher {
    fun find(path: Path):List<String>{
        val f = File(path.toUri()).list()!!
        return f.map{v-> v}
    }
}