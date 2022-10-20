package com.atlassian.pedagogical.frontend.data;

import com.atlassian.json.marshal.Jsonable;
import com.atlassian.webresource.api.data.WebResourceDataProvider;

public class JavaDataProvider implements WebResourceDataProvider {
    @Override
    public final Jsonable get() {
        return writer -> {
            // TODO use a serialisation library like Jackson
            writer.write("{\"key\":\"value\"}");
        };
    }
}
