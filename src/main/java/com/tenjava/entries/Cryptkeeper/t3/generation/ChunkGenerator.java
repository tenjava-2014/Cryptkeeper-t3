package com.tenjava.entries.Cryptkeeper.t3.generation;

import com.tenjava.entries.Cryptkeeper.t3.api.Environment;
import com.tenjava.entries.Cryptkeeper.t3.generation.populators.CakePopulator;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChunkGenerator extends org.bukkit.generator.ChunkGenerator {

    private final Random random = new Random();
    private final List<Environment> environments = new ArrayList<>();

    public void addEnvironment(Environment environment) {
        environments.add(environment);
    }

    @Override
    public byte[] generate(World world, Random random, int cx, int cz) {
        byte[] blocks = new byte[32768];
        Environment environment = getEnvironment();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 128; y++) {
                    blocks[(x * 16 + z) * 128 + y] = (byte) environment.getMaterial(y).getId();
                }
            }
        }
        return blocks;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        List<BlockPopulator> list = new ArrayList<>();
        list.add(new CakePopulator());
        return list;
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 0, 4, 0);
    }

    public List<Environment> getEnvironments() {
        return environments;
    }

    private Environment getEnvironment() {
        double chance = 1;
        while (chance > 0) {
            Environment current = environments.get(random.nextInt(environments.size()));
            chance -= current.getChance();
            if (chance <= 0) {
                return current;
            }
        }
        return null;
    }
}
