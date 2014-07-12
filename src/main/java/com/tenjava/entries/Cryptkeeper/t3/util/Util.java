package com.tenjava.entries.Cryptkeeper.t3.util;

import com.tenjava.entries.Cryptkeeper.t3.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<LivingEntity> getActiveEntities(List<String> allowed) {
        Profiler.profile("getActive");
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
        Profiler.profile("getActive");
        return entities;
    }

    public static Entity getRiding(Entity entity, Entity fallback) {
        while (entity.getVehicle() != null)
            entity = entity.getVehicle();
        if (entity == null)
            entity = fallback;
        return entity;
    }

    public static List<Material> getSafeMaterials(List<String> list) {
        List<Material> safe = new ArrayList<>();
        for (String item : list) {
            try {
                safe.add(Material.valueOf(item));
            } catch (IllegalArgumentException exception) {
                Plugin.getInstance().getLogger().warning("Invalid entry " + item + " for Material");
            }
        }
        return safe;
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

    public static String getName(LivingEntity entity) {
        if (entity instanceof Player)
            return ((Player) entity).getDisplayName();
        return entity.getCustomName();
    }
}
