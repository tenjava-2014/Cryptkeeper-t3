package com.tenjava.cryptkeeper.platforms.runnables;

import com.tenjava.cryptkeeper.platforms.Plugin;
import org.bukkit.World;

public class TimeControl implements Runnable {

    @Override
    public void run() {
        World world = Plugin.getInstance().getWorld();
        world.setTime(world.getTime() + 2L);
    }
}
