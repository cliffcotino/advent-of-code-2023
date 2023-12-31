import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/** Reads lines from the given input txt file. */
fun readInput(name: String) = Path("src/main/resources/$name.txt").readLines()

/** Converts string to md5 hash. */
fun String.md5() =
    BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
        .toString(16)
        .padStart(32, '0')

/** Get the string between start and end */
fun String.substringBetween(start: String, end: String): String =
    substringAfter(start).substringBefore(end)

fun String.splitToLongs(): List<Long> {
    return trim().split(Regex("\\s+")).map { it.toLong() }
}

fun String.splitToInts(): List<Int> {
    return trim().split(Regex("\\s+")).map { it.toInt() }
}

/** Assert that actual == expected */
fun <T> assertEquals(actual: T, expected: T) {
    if (actual != expected) {
        throw AssertionError("actual != expected => '$actual' != '$expected'")
    }
}

/**
 * Generates all the different permutations of the list, whereby we use replacement
 *
 * ```
 * listOf(1, 2).permutations() = [[1, 2], [2, 1]]
 * ```
 */
fun <T> List<T>.permutations(): List<List<T>> =
    if (size <= 1) {
        listOf(this)
    } else {
        drop(1)
            .permutations()
            .map { perm -> indices.map { i -> perm.subList(0, i) + first() + perm.drop(i) } }
            .flatten()
    }

/**
 * Generates all the different permutations of the list, whereby we use repetition
 *
 * ```
 * listOf(1, 2).permutations(2) = [[1, 1], [1, 2], [2, 1], [2, 2]]
 * ```
 */
fun <T> List<T>.permutations(length: Int, acc: List<T> = listOf()): List<List<T>> {
    return if (acc.size == length) {
        listOf(acc)
    } else {
        indices.flatMap { i -> permutations(length, acc + this[i]) }
    }
}

tailrec fun gcd(x: Long, y: Long): Long {
    return if (y == 0L) x else gcd(y, x % y)
}

fun gcd(vararg numbers: Long): Long {
    return numbers.fold(0L) { acc: Long, l: Long -> gcd(acc, l) }
}

fun lcm(vararg numbers: Long): Long {
    return numbers.fold(1L) { acc: Long, l: Long -> acc * (l / gcd(acc, l)) }
}

/** The cleaner shorthand for printing output. */
fun Any?.println() = println(this)

/** Measure how long the given block calculation takes to execute */
fun measure(block: () -> Unit) {
    val start = System.currentTimeMillis()
    block()
    println("Execution duration: ${System.currentTimeMillis() - start} ms")
}

fun List<String>.groupConsecutiveBy(predicate: (String) -> Boolean): List<List<String>> {
    val groups = mutableListOf<List<String>>()
    var lastGroup = mutableListOf<String>()
    forEach { line ->
        if (predicate.invoke(line)) {
            lastGroup.add(line)
        } else {
            groups.add(lastGroup)
            lastGroup = mutableListOf()
        }
    }
    if (lastGroup.isNotEmpty()) {
        groups.add(lastGroup)
    }
    return groups
}
