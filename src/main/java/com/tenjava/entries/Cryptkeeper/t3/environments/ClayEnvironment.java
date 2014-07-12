package com.tenjava.entries.Cryptkeeper.t3.environments;

import com.tenjava.entries.Cryptkeeper.t3.api.Environment;
import org.bukkit.Material;

public class ClayEnvironment extends Environment {

    @Override
    public Material getMaterial(int y) {
        if (y <= 3) {
            return random.nextBoolean() ? Material.WATER : Material.CLAY;
        }
        return Material.AIR;
    }

    @Override
    public double getChance() {
        return 0.05;
    }
}
