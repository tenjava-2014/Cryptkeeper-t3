package com.tenjava.entries.Cryptkeeper.t3.generation.populators;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class CakePopulator extends BlockPopulator {

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        System.out.println("Cake!");
    }
}
