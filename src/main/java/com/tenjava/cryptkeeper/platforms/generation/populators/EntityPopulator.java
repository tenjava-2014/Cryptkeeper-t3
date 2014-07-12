package com.tenjava.cryptkeeper.platforms.generation.populators;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.generator.BlockPopulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityPopulator extends BlockPopulator {

    private final List<EntityType> types = new ArrayList<>();

    public EntityPopulator() {
        for (EntityType type : EntityType.values()) {
            if (type.isAlive() && type.isSpawnable()) {
                types.add(type);
            }
        }
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        int amount = 1 + random.nextInt(3);
        for (int i = 0; i < amount; i++) {
            int x = random.nextInt(16);
            int z = random.nextInt(16);
            Block block = world.getHighestBlockAt(chunk.getX() + x, chunk.getZ() + z);
            block = block.getRelative(BlockFace.UP);
            world.spawnEntity(block.getLocation(), types.get(random.nextInt(types.size())));
        }
    }
}
