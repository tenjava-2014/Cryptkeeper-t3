package com.tenjava.cryptkeeper.platforms.listeners;

import com.tenjava.cryptkeeper.platforms.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().getWorld().getName().equals(Plugin.getInstance().getWorld().getName())) {
            event.setRespawnLocation(Plugin.getInstance().getGenerator().getSpawnLocation());
        }
    }
}
