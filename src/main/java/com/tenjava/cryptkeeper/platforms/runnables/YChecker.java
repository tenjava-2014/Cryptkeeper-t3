package com.tenjava.cryptkeeper.platforms.runnables;

import com.tenjava.cryptkeeper.platforms.Plugin;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class YChecker implements Runnable {

    private final int minY;

    public YChecker() {
        minY = Plugin.getInstance().getConfig().getInt("startY");
    }

    @Override
    public void run() {
        World world = Plugin.getInstance().getWorld();
        for (Player player : world.getPlayers()) {
            if (!player.getGameMode().equals(GameMode.CREATIVE) && player.getLocation().getY() < minY && !player.isDead()) {
                player.damage(player.getHealth());
            }
        }
    }
}
