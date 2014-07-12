package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.Plugin;
import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.LivingEntity;

public class RandomAgeAction extends ActionHandler<LivingEntity> {

    @Override
    public void load(ConfigurationSection section) {
        super.load(section);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Plugin.getInstance(), new Runnable() {

            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    for (LivingEntity entity : world.getLivingEntities()) {
                        if (canActivate(entity, world)) {
                            activate(entity, world);
                        }
                    }
                }
            }
        }, 10 * 20L, 10 * 20L);
    }

    @Override
    public void activate(LivingEntity target, World world) {
        Ageable ageable = (Ageable) target;
        if (!ageable.isAdult()) {
            ageable.setAdult();
        } else {
            ageable.setBaby();
        }
    }

    @Override
    public boolean canActivate(LivingEntity target, World world) {
        if (!super.canActivate(target, world))
            return false;
        if (!(target instanceof Ageable))
            return false;
        return random.nextDouble() <= chance;
    }

    @Override
    public String getSectionName() {
        return "random_age";
    }
}
