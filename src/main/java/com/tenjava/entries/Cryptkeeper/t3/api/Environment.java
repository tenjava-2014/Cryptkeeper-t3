package com.tenjava.entries.Cryptkeeper.t3.api;

import org.bukkit.Material;

import java.util.List;
import java.util.Random;

public class Environment {

    protected final Random random = new Random();
    protected final List<Material> materials;
    protected final double chance;

    public Environment(List<Material> materials, double chance) {
        this.materials = materials;
        this.chance = chance;
    }

    public Material getMaterial(int y) {
        if (y <= 3)
            return materials.get(random.nextInt(materials.size()));
        return Material.AIR;
    }

    public double getChance() {
        return chance;
    }
}
