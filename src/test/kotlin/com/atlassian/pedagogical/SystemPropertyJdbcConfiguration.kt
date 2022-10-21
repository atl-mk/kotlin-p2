package com.atlassian.pedagogical.ao

import net.java.ao.test.jdbc.AbstractJdbcConfiguration
import net.java.ao.test.jdbc.H2Memory
import net.java.ao.test.jdbc.JdbcConfiguration
import org.slf4j.LoggerFactory

class SystemPropertyJdbcConfiguration : JdbcConfiguration {
    private val log = LoggerFactory.getLogger(SystemPropertyJdbcConfiguration::class.java)
    private val jdbcConfiguration: JdbcConfiguration

    companion object {
        const val PROTOCOL_JTDS = "jdbc:jtds:sqlserver"
        const val PROTOCOL_SQLSERVER = "jdbc:sqlserver"
        private const val PROP_JDBC_PREFIX = "jdbc."
        private const val PROP_JDBC_TYPE = PROP_JDBC_PREFIX + "type"
        private const val PROP_JDBC_USER = PROP_JDBC_PREFIX + "user"
        private const val PROP_JDBC_PASSWORD = PROP_JDBC_PREFIX + "password"
        private const val PROP_JDBC_SCHEMA = PROP_JDBC_PREFIX + "schema"
        const val PROP_JDBC_URL = PROP_JDBC_PREFIX + "url"
    }

    init {
        var url = System.getProperty(PROP_JDBC_URL)
        val user = System.getProperty(PROP_JDBC_USER)
        val password = System.getProperty(PROP_JDBC_PASSWORD)
        var schema = System.getProperty(PROP_JDBC_SCHEMA)
        val jdbcType = System.getProperty(PROP_JDBC_TYPE)
        log.info(
            "System properties: {}={}, {}={}, {}={}, {}={}, {}={}",
            PROP_JDBC_URL, url,
            PROP_JDBC_USER, user,
            PROP_JDBC_PASSWORD, password,
            PROP_JDBC_SCHEMA, schema,
            PROP_JDBC_TYPE, jdbcType
        )
        if (url != null && user != null && password != null) {
            if (url.startsWith(PROTOCOL_JTDS)) {
                val temp = PROTOCOL_SQLSERVER + url.substring(PROTOCOL_JTDS.length)
                log.warn("Rewrote jTDS JDBC URL to Microsoft equivalent. {} -> {}", url, temp)
                url = temp
            }
            // Hack for SQLServer 2012. Currently ActiveObjects test module uses the default implementation of
            // net.java.ao.SchemaConfiguration.shouldManageTable() which always returns true, which in the case
            // of SQLServer 2012 picks up system tables. This does not happen at runtime as the ActiveObjects table
            // uses com.atlassian.activeobjects.ao.PrefixedSchemaConfiguration which is what the unit tests should use
            // as well
            if (schema == null && url.startsWith(PROTOCOL_SQLSERVER)) {
                schema = "dbo"
            }
            jdbcConfiguration = SimpleJdbcConfiguration(url, user, password, schema)
            log.info(
                "Using custom database configurations for AO test. url={}, user={}, password={}, schema={}",
                url, user, password, schema
            )
        } else if ("H2".equals(jdbcType, ignoreCase = true)) {
            //H2Memory doesn't configure DB_CLOSE_DELAY, so after the first connection is closed the schema is
            //destroyed and the tests all fail
            val h2 = H2Memory()
            jdbcConfiguration = SimpleJdbcConfiguration(
                h2.url + ";DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE",
                h2.username, h2.password, h2.schema
            )
            log.info("Using in-memory H2 database for AO test")
        } else {
            throw RuntimeException(
                String.format(
                    "No JDBC arguments specified! You can use append 'h2' or other db profiles. If you are running this in " +
                            "IDE then make sure to set system environment variable %s=h2", PROP_JDBC_TYPE
                )
            )
        }
    }

    override fun getPassword(): String {
        return jdbcConfiguration.password
    }

    override fun getSchema(): String {
        return jdbcConfiguration.schema
    }

    override fun getUrl(): String {
        return jdbcConfiguration.url
    }

    override fun getUsername(): String {
        return jdbcConfiguration.username
    }

    override fun init() {
        jdbcConfiguration.init()
    }

    private class SimpleJdbcConfiguration constructor(
        url: String?,
        user: String,
        password: String,
        schema: String?
    ) :
        AbstractJdbcConfiguration(url, user, password, schema) {
        override fun getDefaultSchema(): String {
            return ""
        }

        override fun getDefaultUrl(): String {
            return ""
        }
    }
}