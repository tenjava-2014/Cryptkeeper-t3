package com.tenjava.entries.Cryptkeeper.t3;

import com.tenjava.entries.Cryptkeeper.t3.api.Environment;
import com.tenjava.entries.Cryptkeeper.t3.generation.ChunkGenerator;
import org.bukkit.Material;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Plugin extends JavaPlugin {

    private static Plugin instance;
    private final ChunkGenerator generator = new ChunkGenerator();

    @Override
    public void onEnable() {
        instance = this;
        List<String> environments = getConfig().getStringList("environments");
        for (String environment : environments) {
            List<Material> materials = new ArrayList<>();
            for (String name : getConfig().getStringList(environment + ".materials")) {
                try {
                    materials.add(Material.valueOf(name));
                } catch (IllegalArgumentException ex) {
                    getLogger().warning("Unknown Material: " + name);
                }
            }
            double chance = getConfig().getDouble(environment + ".chance");
            getLogger().info("Loaded " + environment + " with " + materials.size() + " materials and a " + chance + "% chance.");
            Environment instance = new Environment(materials, chance);
            generator.addEnvironment(instance);
        }
        getLogger().info("Loaded " + generator.getEnvironments().size() + " environments!");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        getServer().getScheduler().cancelTasks(this);
    }

    @Override
    public org.bukkit.generator.ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return generator;
    }

    public static Plugin getInstance() {
        return instance;
    }
}
