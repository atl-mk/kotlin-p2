package com.atlassian.pedagogical.frontend.data

import com.atlassian.json.marshal.Jsonable
import com.atlassian.webresource.api.data.WebResourceDataProvider

class KotlinDataProvider: WebResourceDataProvider{
    override fun get(): Jsonable {
        return Jsonable {
            // TODO use a serialisation library like Jackson
            it.write("{\"key\":\"value\"}")
        }
    }
}