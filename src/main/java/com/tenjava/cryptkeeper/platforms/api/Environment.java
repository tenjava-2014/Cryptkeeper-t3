package com.tenjava.cryptkeeper.platforms.api;

import com.tenjava.cryptkeeper.platforms.Plugin;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Environment {

    protected final Random random = new Random();
    protected final ConfigurationSection section;
    protected final List<Material> materials = new ArrayList<>();
    protected final List<EntityType> entities = new ArrayList<>();

    public Environment(ConfigurationSection section) {
        this.section = section;
        for (String name : section.getStringList("materials")) {
            try {
                materials.add(Material.valueOf(name));
            } catch (IllegalArgumentException ex) {
                Plugin.getInstance().getLogger().warning("Unknown Material: " + name);
            }
        }
        for (String name : section.getStringList("entities")) {
            try {
                entities.add(EntityType.valueOf(name));
            } catch (IllegalArgumentException ex) {
                Plugin.getInstance().getLogger().warning("Unknown EntityType: " + name);
            }
        }
    }

    /**
     * Returns a List of all possible Material types that make up the Environment.
     *
     * @return
     */
    public List<Material> getMaterials() {
        return materials;
    }

    /**
     * Returns whether or not this is a platform on which the Player can spawn.
     *
     * @return
     */
    public boolean canSpawn() {
        return section.getBoolean("canSpawn", true);
    }

    /**
     * Returns the weighted chance of this Environment spawning.
     *
     * @return
     */
    public double getChance() {
        return section.getDouble("chance");
    }

    /**
     * Returns a List of all EntityTypes allowed in this environment.
     *
     * @return
     */
    public List<EntityType> getEntities() {
        return entities;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "materials=" + materials +
                ", entities=" + entities +
                '}';
    }
}
