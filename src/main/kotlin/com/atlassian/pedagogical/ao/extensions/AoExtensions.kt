package com.atlassian.pedagogical.ao.extensions

import com.atlassian.activeobjects.external.ActiveObjects
import net.java.ao.DBParam
import net.java.ao.Query
import net.java.ao.RawEntity

/**
 * Creates an active object with auto type matching in Kotlin.
 *
 * @param varargs of <column name, value>
 * @return <T> entity
 */
inline fun <reified T : RawEntity<Long>> ActiveObjects.createWith(vararg pairs: Pair<String, Any>): T {
    val params = mapOf(*pairs).map { (prop, value) -> DBParam(prop, value) }.toTypedArray()
    return create(T::class.java, *params)
}

inline fun <reified T : RawEntity<Long>> ActiveObjects.find(query: Query): Array<T>? = find(T::class.java, query)