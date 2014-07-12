package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;

import java.util.List;
import java.util.Random;

public class VampirePigAction implements ActionHandler<LivingEntity> {

    private final static Random RANDOM = new Random();

    private List<String> worlds;
    private double chance;

    @Override
    public void load(ConfigurationSection section) {
        chance = section.getDouble("chance");
        worlds = section.getStringList("worlds");
    }

    @Override
    public void activate(LivingEntity target, World world) {
        target.damage(target.getHealth());
        target.getWorld().createExplosion(target.getLocation(), RANDOM.nextFloat() + 1F, true);
    }

    @Override
    public boolean canActivate(LivingEntity target, World world) {
        if (!worlds.contains(world.getName()))
            return false;
        if (!(target instanceof Pig))
            return false;
        return RANDOM.nextDouble() <= chance;
    }

    @Override
    public String getSectionName() {
        return "vampire_pig";
    }

    @Override
    public String toString() {
        return "VampirePigAction{" +
                "chance=" + chance +
                '}';
    }
}
