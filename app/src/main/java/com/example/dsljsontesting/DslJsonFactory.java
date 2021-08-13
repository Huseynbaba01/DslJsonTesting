package com.example.dsljsontesting;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.runtime.Settings;

public class DslJsonFactory {
    public static DslJson<Object> create() {
        return new DslJson<>(Settings.basicSetup().allowArrayFormat(true).skipDefaultValues(false));
    }
}
