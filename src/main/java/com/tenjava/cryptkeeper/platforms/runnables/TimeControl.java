package com.tenjava.cryptkeeper.platforms.runnables;

import com.tenjava.cryptkeeper.platforms.Plugin;
import org.bukkit.World;

public class TimeControl implements Runnable {

    private final World world;

    public TimeControl() {
        world = Plugin.getInstance().getWorld();
    }

    @Override
    public void run() {
        world.setTime(world.getTime() + 2L);
    }
}
