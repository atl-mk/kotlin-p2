package com.atlassian.pedagogical.ao.entity

import net.java.ao.Accessor
import net.java.ao.Preload
import net.java.ao.RawEntity
import net.java.ao.schema.AutoIncrement
import net.java.ao.schema.NotNull
import net.java.ao.schema.PrimaryKey

@Preload
interface SampleEntity : RawEntity<Long> {

    companion object {
        const val ID_COLUMN = "ID"
        const val NAME_COLUMN = "NAME"
        const val EVENT_TIMESTAMP_COLUMN = "EVENT_TIMESTAMP"
    }

    val id: Long
        @AutoIncrement
        @NotNull
        @PrimaryKey(ID_COLUMN)
        get

    val name: String
        @Accessor(NAME_COLUMN)
        @NotNull get

    val eventTimestamp: Long
        @Accessor(EVENT_TIMESTAMP_COLUMN)
        @NotNull get
}