package com.atlassian.pedagogical.ao.dao

import com.atlassian.activeobjects.external.ActiveObjects
import com.atlassian.pedagogical.ao.entity.SampleEntity
import com.atlassian.pedagogical.ao.extensions.createWith
import com.atlassian.pedagogical.ao.extensions.find
import com.atlassian.sal.api.transaction.TransactionTemplate
import net.java.ao.Query

class DefaultSampleEntityDao constructor(
    private val ao: ActiveObjects,
    private val transactionTemplate: TransactionTemplate
) : SampleEntityDao {

    override fun save(name: String) {
        val eventTimestamp = System.currentTimeMillis()

        transactionTemplate.execute {
            val sampleEntity = ao.createWith<SampleEntity>(
                SampleEntity.NAME_COLUMN to name,
                SampleEntity.EVENT_TIMESTAMP_COLUMN to eventTimestamp
            )

            sampleEntity.save()
        }
    }

    override fun get(name: String): SampleEntity? {
        return ao.find<SampleEntity>(
            Query.select().where(SampleEntity.NAME_COLUMN + " = ?", name)
        )?.firstOrNull()
    }

}