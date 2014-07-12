package com.tenjava.entries.Cryptkeeper.t3;

import com.tenjava.entries.Cryptkeeper.t3.api.Environment;
import com.tenjava.entries.Cryptkeeper.t3.generation.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Plugin extends JavaPlugin {

    private static Plugin instance;
    private final ChunkGenerator generator = new ChunkGenerator();

    @Override
    public void onEnable() {
        instance = this;
        List<String> environments = getConfig().getStringList("environments");
        for (String sectionName : environments) {
            Environment environment = new Environment(getConfig().getConfigurationSection(sectionName));
            generator.getEnvironments().add(environment);
            getLogger().info("Loaded: " + environment.toString());
        }
        getLogger().info("Loaded " + generator.getEnvironments().size() + " environments!");
    }

    @Override
    public org.bukkit.generator.ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return generator;
    }

    public static Plugin getInstance() {
        return instance;
    }
}
