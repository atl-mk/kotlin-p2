package com.atlassian.pedagogical

class FooImpl : Foo {
    override fun hi(): String {
        return "Not yet implemented";
    }

    internal fun plusFive(num: Int): Int {
        return num + 5
    }
}