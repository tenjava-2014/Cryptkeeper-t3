package com.tenjava.cryptkeeper.platforms.generation.populators;

import com.tenjava.cryptkeeper.platforms.Plugin;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class HolePopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (random.nextInt(5) > 0) {
            return;
        }
        int startX = chunk.getX() + random.nextInt(4) + 4;
        int startZ = chunk.getZ() + random.nextInt(4) + 4;
        int size = random.nextInt(6) + 3;
        int minY = Plugin.getInstance().getConfig().getInt("startY");
        int maxY = minY + Plugin.getInstance().getConfig().getInt("yVariation") + Plugin.getInstance().getConfig().getInt("maxHeight");
        for (int x = 0; x < size; x++) {
            for (int z = 0; z < size; z++) {
                for (int y = minY; y < maxY; y++) {
                    chunk.getBlock(startX + x, y, startZ + z).setType(Material.AIR);
                }
            }
        }
    }
}
