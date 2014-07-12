package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.Plugin;
import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import com.tenjava.entries.Cryptkeeper.t3.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public class FlyingAnimalAction implements ActionHandler<LivingEntity>, Runnable {

    private final static Random RANDOM = new Random();

    private List<String> worlds;
    private List<EntityType> types;
    private double chance;

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

    @Override
    public void load(ConfigurationSection section) {
        chance = section.getDouble("chance");
        worlds = section.getStringList("worlds");
        types = Util.getSafeEntities(section.getStringList("entities"));
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Plugin.getInstance(), this, 40L, 40L);
    }

    @Override
    public void activate(LivingEntity target, World world) {
        Bat bat = world.spawn(target.getLocation(), Bat.class);
        bat.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0), true);
        bat.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0), true);
        bat.setPassenger(Util.getRiding(target, target));
    }

    @Override
    public boolean canActivate(LivingEntity target, World world) {
        if (!types.contains(target.getType()))
            return false;
        if (!worlds.contains(world.getName()))
            return false;
        if (!(target instanceof Chicken))
            return false;
        //return RANDOM.nextDouble() <= chance;
        return true;
    }

    @Override
    public String getSectionName() {
        return "flying_animal";
    }

    @Override
    public String toString() {
        return "FlyingAnimalAction{" +
                "chance=" + chance +
                '}';
    }
}
