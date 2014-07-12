package com.tenjava.entries.Cryptkeeper.t3.environments;

import com.tenjava.entries.Cryptkeeper.t3.api.Environment;
import org.bukkit.Material;

public class EnderEnvironment implements Environment {

    @Override
    public Material getMaterial(int y) {
        if (y <= 15) {
            return Material.ENDER_STONE;
        }
        return Material.AIR;
    }
}
