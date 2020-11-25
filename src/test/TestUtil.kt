package test

import java.lang.reflect.Field
import java.lang.reflect.Method

fun <T> Any.forceGetValue(name: String): T = javaClass.getDeclaredField(name).let {
    it.isAccessible = true
    @Suppress("UNCHECKED_CAST")
    it.get(this) as T
}

fun  Any.forceGetFunction(name: String, vararg parameters: Class<*>): Method = javaClass.getDeclaredMethod(name,*parameters).also {
    it.isAccessible = true
}
