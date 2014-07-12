package com.tenjava.cryptkeeper.platforms.listeners;

import com.tenjava.cryptkeeper.platforms.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class SpawnListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().teleport(Plugin.getInstance().getWorld().getSpawnLocation());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(Plugin.getInstance().getWorld().getSpawnLocation());
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityCombust(EntityCombustEvent event) {
        if (event.getDuration() == 8) {
            event.setCancelled(true);
        }
    }
}
