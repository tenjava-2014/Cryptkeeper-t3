package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.Plugin;
import com.tenjava.entries.Cryptkeeper.t3.api.EntityActionHandler;
import com.tenjava.entries.Cryptkeeper.t3.util.Profiler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Bat;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FlippedMobAction extends EntityActionHandler<LivingEntity> implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntitySpawn(CreatureSpawnEvent event) {
        if (!event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM) && canActivate(event.getEntity(), event.getEntity().getWorld())) {
            activate(event.getEntity(), event.getEntity().getWorld());
        }
    }

    @Override
    public void register() {
        Bukkit.getPluginManager().registerEvents(this, Plugin.getInstance());
    }

    @Override
    public void activate(LivingEntity target, World world) {
        Profiler.profile("flippedActivate");
        Bat bat = world.spawn(target.getLocation(), Bat.class);
        bat.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0), true);
        bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0), true);
        target.setPassenger(bat);
        target.setCustomName("Dinnerbone");
        Profiler.profile("flippedActivate");
    }

    @Override
    public boolean canActivate(LivingEntity entity, World world) {
        if (!super.canActivate(entity, world))
            return false;
        if (entity.getPassenger() != null)
            return false;
        if (entity.getVehicle() != null)
            return false;
        return true;
    }

    @Override
    public String getSectionName() {
        return "flipped_mob";
    }
}
