package com.tenjava.entries.Cryptkeeper.t3.generation;

import com.tenjava.entries.Cryptkeeper.t3.generation.populators.CakePopulator;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.List;

public class ChunkGenerator extends org.bukkit.generator.ChunkGenerator {

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        List<BlockPopulator> list = super.getDefaultPopulators(world);
        list.add(new CakePopulator());
        return list;
    }
}
