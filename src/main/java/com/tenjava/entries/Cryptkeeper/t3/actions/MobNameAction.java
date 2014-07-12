package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.Plugin;
import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.ArrayList;
import java.util.List;

public class MobNameAction extends ActionHandler<LivingEntity> implements Listener {

    private final List<String> names = new ArrayList<>();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntitySpawn(CreatureSpawnEvent event) {
        if (!event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CUSTOM) && canActivate(event.getEntity(), event.getEntity().getWorld())) {
            activate(event.getEntity(), event.getEntity().getWorld());
        }
    }

    @Override
    public void load(ConfigurationSection section) {
        super.load(section);
        names.addAll(section.getStringList("names"));
    }

    @Override
    public void register() {
        Bukkit.getPluginManager().registerEvents(this, Plugin.getInstance());
    }

    @Override
    public void activate(LivingEntity target, World world) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            builder.append(names.get(random.nextInt(names.size())));
            if (i < 2) {
                builder.append(" ");
            }
        }
        target.setCustomName(builder.toString());
        target.setCustomNameVisible(true);
    }

    @Override
    public boolean canActivate(LivingEntity entity, World world) {
        if (!super.canActivate(entity, world))
            return false;
        if (entity instanceof Player)
            return false;
        return random.nextDouble() <= chance;
    }

    @Override
    public String getSectionName() {
        return "mob_names";
    }
}
