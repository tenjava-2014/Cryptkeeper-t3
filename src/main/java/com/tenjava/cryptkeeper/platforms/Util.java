package com.tenjava.cryptkeeper.platforms;

import org.bukkit.Chunk;
import org.bukkit.block.Block;

import java.util.Random;

public class Util {

    private final static Random random = new Random();

    /**
     * Returns a random solid block on the top-level of the Chunk.
     *  If it cannot locate one in 10 attempts, it will return the last attempt.
     *
     * @param chunk
     * @return
     */
    public static Block getSolidBlock(Chunk chunk) {
        Block block = null;
        int passovers = 0;
        while ((block == null || !block.getType().isSolid()) && passovers < 10) {
            block = chunk.getWorld().getHighestBlockAt(chunk.getX() + random.nextInt(16), chunk.getZ() + random.nextInt(16));
            passovers++;
        }
        return block;
    }
}
