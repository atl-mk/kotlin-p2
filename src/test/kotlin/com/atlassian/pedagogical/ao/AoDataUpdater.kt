package com.atlassian.pedagogical.ao

import com.atlassian.pedagogical.ao.entity.SampleEntity
import net.java.ao.EntityManager
import net.java.ao.test.jdbc.DatabaseUpdater

internal class AoDataUpdater : DatabaseUpdater {
    @Throws(Exception::class)
    override fun update(entityManager: EntityManager) {
        entityManager.migrate(SampleEntity::class.java)
    }
}