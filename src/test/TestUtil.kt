@file:Suppress("UNCHECKED_CAST")

package test

import org.junit.jupiter.api.Assertions
import java.io.File
import java.lang.reflect.Method
import java.nio.file.Path
import java.util.*

fun <T> Any.forceGetValue(name: String): T = javaClass.getDeclaredField(name).let {
    it.isAccessible = true
    @Suppress("UNCHECKED_CAST")
    it.get(this) as T
}

fun Any.forceGetFunction(name: String, vararg parameters: Class<*>): Function =
    Function(target = this, method = javaClass.getDeclaredMethod(name, *parameters).also { it.isAccessible = true })


class Function(val target: Any, val method: Method){
    fun <T> invoke(vararg parameters: Any):T{
        val result = method.invoke(target, *parameters)
        return result as T
    }
}

fun createTestSauceData(dir: Path): List<File> {
    val fourCh = dir.resolve("4CH")
    Assertions.assertTrue(fourCh.toFile().mkdir())
    val folder1 = fourCh.resolve("FOLDER01")
    val folder2 = fourCh.resolve("FOLDER02")
    val targetFileMS11 = folder1.resolve("SR001MS.WAV")
    val targetFileXY11 = folder1.resolve("SR001XY.WAV")
    val targetFileMS21 = folder2.resolve("SR001MS.WAV")
    val targetFileXY21 = folder2.resolve("SR001XY.WAV")
    val targetFileMS11File = targetFileMS11.toFile()
    val targetFileXY11File = targetFileXY11.toFile()
    val targetFileMS21File = targetFileMS21.toFile()
    val targetFileXY21File = targetFileXY21.toFile()
    val files = arrayListOf(targetFileMS11File, targetFileXY11File, targetFileMS21File, targetFileXY21File)
    Assertions.assertTrue(folder1.toFile().mkdir())
    Assertions.assertTrue(folder2.toFile().mkdir())
    targetFileMS11File.createNewFile()
    targetFileXY11File.createNewFile()
    targetFileMS21File.createNewFile()
    targetFileXY21File.createNewFile()
    targetFileMS11File.setLastModified(Calendar.getInstance().let {
        it.set(2019, 5, 1, 2, 3, 4)
        it.timeInMillis
    })
    targetFileXY11File.setLastModified(Calendar.getInstance().let {
        it.set(2019, 5, 1, 2, 3, 4)
        it.timeInMillis
    })
    targetFileMS21File.setLastModified(Calendar.getInstance().let {
        it.set(2019, 10, 12, 13, 14, 15)
        it.timeInMillis
    })
    targetFileXY21File.setLastModified(Calendar.getInstance().let {
        it.set(2019, 10, 12, 13, 14, 15)
        it.timeInMillis
    })

    return files
}