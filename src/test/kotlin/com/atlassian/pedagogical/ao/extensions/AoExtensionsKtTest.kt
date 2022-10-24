package com.atlassian.pedagogical.ao.extensions

import com.atlassian.activeobjects.external.ActiveObjects
import com.atlassian.pedagogical.ao.entity.SampleEntity
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import net.java.ao.DBParam
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class AoExtensionsKtTest {

    @RelaxedMockK
    private lateinit var ao: ActiveObjects

    @Test
    fun `createWith() should map AO DBParam correctly`() {

        ao.createWith<SampleEntity>(
            "id" to "idValue",
            "someOtherColumn" to "columnValue"
        )

        verify(exactly = 1) {
            ao.create(
                SampleEntity::class.java,
                DBParam("id", "idValue"),
                DBParam("someOtherColumn", "columnValue")
            )
        }

    }

}