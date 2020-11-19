package test

import java.lang.reflect.Field

fun <T> Any.forceGetValue(name: String): T = javaClass.getDeclaredField(name).let {
    it.isAccessible = true
    @Suppress("UNCHECKED_CAST")
    it.get(this) as T
}
