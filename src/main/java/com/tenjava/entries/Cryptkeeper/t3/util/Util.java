package com.tenjava.entries.Cryptkeeper.t3.util;

import com.tenjava.entries.Cryptkeeper.t3.Plugin;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Util {

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
