package com.oglofus.gringotts.towny;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.function.BiFunction;

import static org.gestern.gringotts.Util.translateColors;

public enum TownyLanguage {
    LANG;
    public String noTownVaultPerm;
    public String noTownResident;
    public String noNationVaultPerm;
    public String notInNation;

    public void readLanguage(FileConfiguration savedLanguage) {
        BiFunction<String, String, String> translator =
                (path, def) -> translateColors(savedLanguage.getString(path, def));

        //towny plugin
        LANG.noTownVaultPerm   =
                translator.apply("noTownPerm", "You do not have permission to create town vaults here.");
        LANG.noTownResident    =
                translator.apply("noTownResident", "Cannot create town vault: You are not resident of a town.");
        LANG.noNationVaultPerm =
                translator.apply("NoNationVaultPerm", "You do not have permission to create nation vaults here.");
        LANG.notInNation       =
                translator.apply("notInNation", "Cannot create nation vault: You do not belong to a nation.");

    }
}
