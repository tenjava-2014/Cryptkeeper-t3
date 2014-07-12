package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.Plugin;
import com.tenjava.entries.Cryptkeeper.t3.api.EntityActionHandler;
import com.tenjava.entries.Cryptkeeper.t3.util.Profiler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class MobColumnAction extends EntityActionHandler<LivingEntity> implements Listener {

    private int maxSize, minSize;

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntitySpawn(CreatureSpawnEvent event) {
        if (!event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM) && canActivate(event.getEntity(), event.getEntity().getWorld())) {
            activate(event.getEntity(), event.getEntity().getWorld());
        }
    }

    @Override
    public void load(ConfigurationSection section) {
        super.load(section);
        minSize = section.getInt("min_size");
        maxSize = section.getInt("max_size");
    }

    @Override
    public void register() {
        Bukkit.getPluginManager().registerEvents(this, Plugin.getInstance());
    }

    @Override
    public void activate(LivingEntity target, World world) {
        System.out.println("Activating: " + getClass().getSimpleName());
        Profiler.profile("columnActivate");
        int size = random.nextInt(maxSize - minSize) + minSize;
        LivingEntity last = target;
        for (int i = 0; i < size; i++) {
            LivingEntity other = (LivingEntity) world.spawnEntity(target.getLocation(), target.getType());
            last.setPassenger(other);
            last = other;
        }
        Profiler.profile("columnActivate");
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
        return "mob_column";
    }

    @Override
    public String toString() {
        return "MobColumnAction{" +
                "maxSize=" + maxSize +
                ", minSize=" + minSize +
                '}';
    }
}
