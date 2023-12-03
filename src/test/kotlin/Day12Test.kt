
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {

    private val day = Day12()

    @Test
    fun `test1 sample`() {
        val actual = day.test1("sample")
        assertEquals(31, actual)
    }

    @Test
    fun `test2 sample`() {
        val actual = day.test2("sample")
        assertEquals(-1, actual)
    }

    @Test
    fun `test1 actual`() {
        val actual = day.test1("input")
        assertEquals(-1, actual)
    }

    @Test
    fun `test2 actual`() {
        val actual = day.test2("input")
        assertEquals(-1, actual)
    }
}