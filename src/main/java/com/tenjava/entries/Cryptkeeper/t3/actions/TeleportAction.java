package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.Plugin;
import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import com.tenjava.entries.Cryptkeeper.t3.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class TeleportAction extends ActionHandler<LivingEntity> {

    private int maxRange;

    @Override
    public void load(ConfigurationSection section) {
        super.load(section);
        maxRange = section.getInt("max_range");
    }

    @Override
    public void register() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Plugin.getInstance(), new Runnable() {

            @Override
            public void run() {
                for (LivingEntity entity : Util.getActiveEntities()) {
                    if (canActivate(entity, entity.getWorld())) {
                        activate(entity, entity.getWorld());
                    }
                }
            }
        }, 30L, 30L);
    }

    @Override
    public void activate(LivingEntity target, World world) {
        Location end = target.getLocation().add((random.nextBoolean() ? -1 : 1) * random.nextInt(maxRange), 0, (random.nextBoolean() ? -1 : 1) * random.nextInt(maxRange));
        target.teleport(end);
    }

    @Override
    public boolean canActivate(LivingEntity target, World world) {
        if (!super.canActivate(target, world))
            return false;
        if (target instanceof Player)
            return false;
        return true;
    }

    @Override
    public String getSectionName() {
        return "teleport";
    }

    @Override
    public String toString() {
        return "TeleportAction{" +
                "maxRange=" + maxRange +
                '}';
    }
}
