package com.mobile.testing.utils.properties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:app.properties"
})
public interface MobitruCloudConfig extends Config {

    @Key("mobitru.project.name")
    @DefaultValue("epm-tstf")
    String mobitruProjectName();

    @Key("mobitru.auth.key")
    String mobitruAuthKey();
}
