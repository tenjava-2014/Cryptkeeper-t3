package com.tenjava.cryptkeeper.platforms.generation.populators;

import com.tenjava.cryptkeeper.platforms.Util;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class TorchPopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        Block block = Util.getSolidBlock(chunk);
        for (int y = 0; y < 3; y++) {
            block = block.getRelative(BlockFace.UP);
            block.setType(Material.FENCE);
        }
        block.getRelative(BlockFace.UP).setType(Material.TORCH);
    }
}
