package com.tenjava.entries.Cryptkeeper.t3;

import com.tenjava.entries.Cryptkeeper.t3.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<LivingEntity> getActiveEntities(List<String> allowed) {
        List<LivingEntity> entities = new ArrayList<>();
        for (World world : Bukkit.getWorlds()) {
            if (!allowed.contains(world.getName()))
                continue;
            if (world.getPlayers().isEmpty())
                continue;
            for (Chunk chunk : world.getLoadedChunks()) {
                for (Entity entity : chunk.getEntities()) {
                    if (entity instanceof LivingEntity) {
                        entities.add((LivingEntity) entity);
                    }
                }
            }
        }
        return entities;
    }

    public static Entity getRiding(Entity entity, Entity fallback) {
        while (entity.getVehicle() != null)
            entity = entity.getVehicle();
        if (entity == null)
            entity = fallback;
        return entity;
    }

    public static List<EntityType> getSafeEntities(List<String> list) {
        List<EntityType> safe = new ArrayList<>();
        for (String item : list) {
            try {
                safe.add(EntityType.valueOf(item));
            } catch (IllegalArgumentException exception) {
                Plugin.getInstance().getLogger().warning("Invalid entry " + item + " for EntityType");
            }
        }
        return safe;
    }
}
