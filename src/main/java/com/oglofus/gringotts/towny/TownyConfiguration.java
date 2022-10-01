package com.oglofus.gringotts.towny;

import org.bukkit.configuration.file.FileConfiguration;

public enum TownyConfiguration {
    CONF;

    /**
     * Language to be used for messages. Should be an ISO 639-1 (alpha-2) code.
     * If a language is not supported by Gringotts, use user-configured or default (English) messages.
     */
    public String language           = "custom";
    public String townSignTypeName   = "town";
    public String nationSignTypeName = "nation";

    public boolean vaultsOnlyInTowns = false;

    public void readConfig(FileConfiguration savedConfig) {
        CONF.language           = savedConfig.getString("language", "custom");
        CONF.townSignTypeName   = savedConfig.getString("town_sign_type_name", "town");
        CONF.nationSignTypeName = savedConfig.getString("nation_sign_type_name", "nation");
        CONF.vaultsOnlyInTowns  = savedConfig.getBoolean("vaults_only_in_towns", false);
    }
}
