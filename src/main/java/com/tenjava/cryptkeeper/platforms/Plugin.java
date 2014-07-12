package com.tenjava.cryptkeeper.platforms;

import com.tenjava.cryptkeeper.platforms.api.Environment;
import com.tenjava.cryptkeeper.platforms.generation.ChunkGenerator;
import com.tenjava.cryptkeeper.platforms.listeners.SpawnListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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
        getServer().getPluginManager().registerEvents(new SpawnListener(), this);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

            @Override
            public void run() {
                Location spawn = generator.getSpawnLocation();
                getWorld().setSpawnLocation(spawn.getBlockX(), spawn.getBlockY(), spawn.getBlockX());
                getWorld().setKeepSpawnInMemory(true);
                getWorld().setSpawnFlags(false, false);
                getWorld().setGameRuleValue("doDaylightCycle", "false");
                getWorld().setTime(14200L);
            }
        });
    }

    @Override
    public org.bukkit.generator.ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return generator;
    }

    public World getWorld() {
        return getServer().getWorld(getConfig().getString("targetWorld"));
    }

    public ChunkGenerator getGenerator() {
        return generator;
    }

    public static Plugin getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "Plugin{" +
                "generator=" + generator +
                '}';
    }
}
