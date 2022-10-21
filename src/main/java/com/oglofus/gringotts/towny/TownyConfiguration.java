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

    /**
     * Maximum number of vaults a town/nation can have, -1 equals no restriction
     */
    public int     maxTownVaults      = -1;
    public int     maxNationVaults    = -1;
    public boolean vaultsOnlyInTowns  = false;
    public long    townStartBalance   = 0;
    public long    nationStartBalance = 0;

    public void readConfig(FileConfiguration savedConfig) {
        CONF.language           = savedConfig.getString("language", "custom");
        CONF.townSignTypeName   = savedConfig.getString("town_sign_type_name", "town");
        CONF.nationSignTypeName = savedConfig.getString("nation_sign_type_name", "nation");
        CONF.maxTownVaults      = savedConfig.getInt("max_town_vaults", -1);
        CONF.maxNationVaults    = savedConfig.getInt("max_nation_vaults", -1);
        CONF.vaultsOnlyInTowns  = savedConfig.getBoolean("vaults_only_in_towns", false);
        CONF.townStartBalance   = savedConfig.getLong("town_start_balance", 0);
        CONF.nationStartBalance = savedConfig.getLong("nation_start_balance", 0);
    }
}
