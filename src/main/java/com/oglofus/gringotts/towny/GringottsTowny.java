package com.oglofus.gringotts.towny;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.gestern.gringotts.Gringotts;
import org.gestern.gringotts.dependency.towny.TownyDependency;

/**
 * The type Gringotts towny.
 */
public class GringottsTowny extends JavaPlugin {
    private static final String DEPENDENCY_ID = "gringotts-towny";

    @Override
    public void onLoad() {
        try {
            Plugin plugin = Gringotts.instance.getDependencies()
                    .hookPlugin("Towny", "com.palmergames.bukkit.towny.Towny", "0.97");

            if (plugin != null) {
                if (!Gringotts.instance.getDependencies()
                        .registerDependency(new TownyDependency(Gringotts.instance, plugin))) {
                    getLogger().warning("Towny plugin is already assigned into the dependencies.");
                }
            }
        } catch (IllegalArgumentException e) {
            getLogger().warning("Looks like Towny plugin is not compatible with Gringotts");
        }
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
