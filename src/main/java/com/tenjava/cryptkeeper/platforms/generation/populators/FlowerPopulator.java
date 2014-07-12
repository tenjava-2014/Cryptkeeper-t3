package com.tenjava.cryptkeeper.platforms.generation.populators;

import com.tenjava.cryptkeeper.platforms.Util;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FlowerPopulator extends BlockPopulator {

    private final List<Material> flowers = Arrays.asList(
            Material.RED_ROSE,
            Material.YELLOW_FLOWER,
            Material.LONG_GRASS
    );

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        if (!random.nextBoolean()) {
            return;
        }

        int target = 3 + random.nextInt(3);

        for (int i = 0; i < target; i++) {
            Block block = Util.getSolidBlock(chunk);

            if (!block.getType().equals(Material.GRASS) && !block.getType().equals(Material.DIRT)) {
                continue;
            }

            block = block.getRelative(BlockFace.UP);
            block.setType(flowers.get(random.nextInt(flowers.size())));
        }
    }
}
