package com.tenjava.entries.Cryptkeeper.t3.environments;

import com.tenjava.entries.Cryptkeeper.t3.api.Environment;
import org.bukkit.Material;

public class StandardEnvironment implements Environment {

    @Override
    public Material getMaterial(int y) {
        if (y <= 16) {
            return Material.STONE;
        }
        if (y <= 24) {
            return Material.DIRT;
        }
        if (y <= 25) {
            return Material.GRASS;
        }
        return Material.AIR;
    }
}
