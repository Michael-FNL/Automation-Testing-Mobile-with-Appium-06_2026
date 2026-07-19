package com.mobile.testing.utils.properties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "classpath:app.properties"
})
public interface SauceLabsConfig extends Config {

    @Key("sauce.username")
    String sauceUsername();

    @Key("sauce.access.key")
    String sauceAccessKey();

    @Key("sauce.data.center")
    @DefaultValue("us-west-1")
    String sauceDataCenter();

    @Key("sauce.build.name")
    @DefaultValue("Local Build")
    String sauceBuildName();
}
