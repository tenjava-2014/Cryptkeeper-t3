package com.tenjava.cryptkeeper.platforms;

import com.tenjava.cryptkeeper.platforms.api.Environment;
import com.tenjava.cryptkeeper.platforms.generation.ChunkGenerator;
import com.tenjava.cryptkeeper.platforms.listeners.LiquidListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    private static Plugin instance;
    private final ChunkGenerator generator = new ChunkGenerator();

    @Override
    public void onEnable() {
        instance = this;
        for (String sectionName : getConfig().getStringList("environments")) {
            generator.getEnvironments().add(new Environment(getConfig().getConfigurationSection(sectionName)));
        }
        getLogger().info("Loaded " + generator.getEnvironments().size() + " environments!");
        getServer().getPluginManager().registerEvents(new LiquidListener(), this);
    }

    @Override
    public org.bukkit.generator.ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return generator;
    }

    public static Plugin getInstance() {
        return instance;
    }
}
