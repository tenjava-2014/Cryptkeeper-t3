package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.Plugin;
import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import com.tenjava.entries.Cryptkeeper.t3.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.List;

public class MobColumnAction extends ActionHandler<LivingEntity> implements Listener {

    private List<EntityType> types;
    private int maxSize, minSize;

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntitySpawn(CreatureSpawnEvent event) {
        if (canActivate(event.getEntity(), event.getEntity().getWorld())) {
            activate(event.getEntity(), event.getEntity().getWorld());
        }
    }

    @Override
    public void load(ConfigurationSection section) {
        super.load(section);
        minSize = section.getInt("min_size");
        maxSize = section.getInt("max_size");
        types = Util.getSafeEntities(section.getStringList("entities"));
        Bukkit.getPluginManager().registerEvents(this, Plugin.getInstance());
    }

    @Override
    public void activate(LivingEntity target, World world) {
        int size = random.nextInt(maxSize - minSize) + minSize;
        LivingEntity last = target;
        for (int i = 0; i < size; i++) {
            LivingEntity other = (LivingEntity) world.spawnEntity(target.getLocation(), target.getType());
            last.setPassenger(other);
            last = other;
        }
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
}
