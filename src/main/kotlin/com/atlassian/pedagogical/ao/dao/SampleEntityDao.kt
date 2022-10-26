package com.atlassian.pedagogical.ao.dao

import com.atlassian.pedagogical.ao.entity.SampleEntity

interface SampleEntityDao {

    fun save(name: String)

    fun get(name: String): SampleEntity?
}