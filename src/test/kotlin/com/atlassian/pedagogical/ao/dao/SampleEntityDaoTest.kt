package com.atlassian.pedagogical.ao.dao

import com.atlassian.activeobjects.test.TestActiveObjects
import com.atlassian.pedagogical.ao.AoDataUpdater
import com.atlassian.pedagogical.ao.SystemPropertyJdbcConfiguration
import com.atlassian.sal.api.transaction.TransactionCallback
import com.atlassian.sal.api.transaction.TransactionTemplate
import net.java.ao.EntityManager
import net.java.ao.test.jdbc.Data
import net.java.ao.test.jdbc.Jdbc
import net.java.ao.test.junit.ActiveObjectsJUnitRunner
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(ActiveObjectsJUnitRunner::class)
@Jdbc(SystemPropertyJdbcConfiguration::class)
@Data(AoDataUpdater::class)
internal class SampleEntityDaoTest {

    private lateinit var entityManager: EntityManager
    private lateinit var underTest: SampleEntityDao

    @Before
    fun setup() {
        val ao = TestActiveObjects(entityManager)
        val transactionTemplate: TransactionTemplate = object : TransactionTemplate {
            override fun <T> execute(transactionCallback: TransactionCallback<T>): T {
                return transactionCallback.doInTransaction()
            }
        }
        underTest = DefaultSampleEntityDao(ao, transactionTemplate)
    }

    @Test
    fun `save() should persist entity with given name`() {
        val entityName = "yay";
        underTest.save(entityName)
        val persisted = underTest.get(entityName)
        assertEquals(entityName, persisted!!.name)
    }

    @Test
    fun `get() should retrieve persisted entity when found`() {
        val entityName = "yay23232";
        underTest.save(entityName)
        val persisted = underTest.get(entityName)
        assertEquals(entityName, persisted!!.name)
    }

    @Test
    fun `get() should return null when value does not exist in db`() {
        val entityName = "blaaa";
        val persisted = underTest.get(entityName)
        assertEquals(null, persisted)
    }

}