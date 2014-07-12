package com.tenjava.cryptkeeper.platforms.generation.populators;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class TentPopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        int startX = chunk.getX() + random.nextInt(4) + 8;
        int startZ = chunk.getZ() + random.nextInt(4) + 8;

    }
}
