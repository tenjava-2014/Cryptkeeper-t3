package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import com.tenjava.entries.Cryptkeeper.t3.util.Util;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Random;

public class LeapingAnimalAction implements ActionHandler<LivingEntity> {

    private final static Random RANDOM = new Random();

    private List<String> worlds;
    private List<EntityType> types;
    private double chance;

    @Override
    public void load(ConfigurationSection section) {
        chance = section.getDouble("chance");
        worlds = section.getStringList("worlds");
        types = Util.getSafeEntities(section.getStringList("entities"));
    }

    @Override
    public void activate(LivingEntity target, World world) {
        target.setVelocity(new Vector(0, 1, 0));
    }

    @Override
    public boolean canActivate(LivingEntity target, World world) {
        if (!worlds.contains(world.getName()))
            return false;
        if (!types.contains(target.getType()))
            return false;
        if (!target.isOnGround())
            return false;
        return RANDOM.nextDouble() <= chance;
    }

    @Override
    public String getSectionName() {
        return "leaping_animal";
    }

    @Override
    public String toString() {
        return "LeapingAnimalAction{" +
                "worlds=" + worlds +
                ", types=" + types +
                ", chance=" + chance +
                '}';
    }
}
