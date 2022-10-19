package com.atlassian.pedagogical

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable

internal class FooImplTest {

    private val foo = FooImpl()

    @Test
    fun `addFive() should return given number plus five`() {
        Assertions.assertAll(
            Executable { assertEquals(6, foo.plusFive(1)) },
            Executable { assertEquals(7, foo.plusFive(2)) },
            Executable { assertEquals(5, foo.plusFive(0)) },
            Executable { assertEquals(-5, foo.plusFive(-10)) }
        )
    }
}