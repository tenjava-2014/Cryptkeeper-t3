package com.tenjava.cryptkeeper.platforms.listeners;

import com.tenjava.cryptkeeper.platforms.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SpawnListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().teleport(Plugin.getInstance().getGenerator().getSpawnLocation());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().getWorld().getName().equals(Plugin.getInstance().getWorld().getName())) {
            event.setRespawnLocation(Plugin.getInstance().getGenerator().getSpawnLocation());
        }
    }
}
