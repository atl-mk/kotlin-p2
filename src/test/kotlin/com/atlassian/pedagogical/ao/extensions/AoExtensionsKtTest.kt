package com.atlassian.pedagogical.ao.extensions

import com.atlassian.activeobjects.external.ActiveObjects
import com.atlassian.pedagogical.ao.entity.SampleEntity
import net.java.ao.DBParam
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

internal class AoExtensionsKtTest {

    private val ao: ActiveObjects = mock(ActiveObjects::class.java)

    @Test
    fun `createWith() should map AO DBParam correctly`() {
        Mockito.`when`(ao.create(eq(SampleEntity::class.java), any(DBParam::class.java)))
            .thenReturn(mock(SampleEntity::class.java))

        ao.createWith<SampleEntity>(
            "id" to "idValue",
            "someOtherColumn" to "columnValue"
        )

        verify(ao).create(
            SampleEntity::class.java,
            DBParam("id", "idValue"),
            DBParam("someOtherColumn", "columnValue")
        )

    }

}