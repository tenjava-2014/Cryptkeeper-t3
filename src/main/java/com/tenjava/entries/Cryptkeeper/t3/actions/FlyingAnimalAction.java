package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.Plugin;
import com.tenjava.entries.Cryptkeeper.t3.api.EntityActionHandler;
import com.tenjava.entries.Cryptkeeper.t3.util.Profiler;
import com.tenjava.entries.Cryptkeeper.t3.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Bat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FlyingAnimalAction extends EntityActionHandler<LivingEntity> {

    @Override
    public void register() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Plugin.getInstance(), new Runnable() {

            @Override
            public void run() {
                for (LivingEntity entity : Util.getActiveEntities(worlds)) {
                    if (canActivate(entity, entity.getWorld())) {
                        activate(entity, entity.getWorld());
                    }
                }
            }
        }, 40L, 40L);
    }

    @Override
    public void activate(LivingEntity target, World world) {
        Profiler.profile("flyingActivate");
        Bat bat = world.spawn(target.getLocation(), Bat.class);
        bat.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0), true);
        bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0), true);
        bat.setPassenger(Util.getRiding(target, target));
        Profiler.profile("flyingActivate");
    }

    @Override
    public boolean canActivate(LivingEntity target, World world) {
        if (!super.canActivate(target, world))
            return false;
        if (!types.contains(target.getType()))
            return false;
        return random.nextDouble() <= chance;
    }

    @Override
    public String getSectionName() {
        return "flying_animal";
    }
}
