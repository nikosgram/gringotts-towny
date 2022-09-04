package com.oglofus.gringotts.towny;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.gestern.gringotts.Gringotts;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * The type Gringotts towny.
 */
@SuppressWarnings("unused") public class GringottsTowny extends JavaPlugin {
    private static final String MESSAGES_YML = "messages.yml";

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

        // load and init configuration
        saveDefaultConfig(); // saves default configuration if no config.yml exists yet
        reloadConfig();
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    /**
     * Reload config.
     * <p>
     * override to handle custom config logic and language loading
     */
    @Override
    public void reloadConfig() {
        super.reloadConfig();
        TownyConfiguration.CONF.readConfig(getConfig());
        TownyLanguage.LANG.readLanguage(getMessages());
    }

    /**
     * Get the configured player interaction messages.
     *
     * @return the configured player interaction messages
     */
    public FileConfiguration getMessages() {
        String langPath = String.format("i18n/messages_%s.yml", TownyConfiguration.CONF.language);

        // try configured language first
        InputStream       langStream = getResource(langPath);
        FileConfiguration conf;

        if (langStream != null) {
            Reader langReader = new InputStreamReader(langStream, StandardCharsets.UTF_8);
            conf = YamlConfiguration.loadConfiguration(langReader);
        } else {
            // use custom/default
            File langFile = new File(getDataFolder(), MESSAGES_YML);
            conf = YamlConfiguration.loadConfiguration(langFile);
        }

        return conf;
    }
}
