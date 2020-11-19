package test

import main.getArgs
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class AppKtTest {


    @Test
    fun testParam0() {
        val args = arrayOf<String>()
        assertEquals(getArgs(args, 0, "1"), "1")
        assertEquals(getArgs(args, 1, "2"), "2")
    }

    @Test
    fun testParam1() {
        val args = arrayOf("a")
        assertEquals(getArgs(args, 0, "1"), "a")
        assertEquals(getArgs(args, 1, "2"), "2")
    }

    @Test
    fun testParam2() {
        val args = arrayOf("a", "b")
        assertEquals(getArgs(args, 0, "1"), "a")
        assertEquals(getArgs(args, 1, "2"), "b")
    }
}
