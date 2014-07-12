package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import com.tenjava.entries.Cryptkeeper.t3.util.Util;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class PoopingAnimalAction implements ActionHandler<LivingEntity> {

    private final static Random RANDOM = new Random();

    private List<String> worlds;
    private List<EntityType> types;
    private double chance;

    @Override
    public void load(ConfigurationSection section) {
        chance = section.getDouble("chance");
        types = Util.getSafeEntities(section.getStringList("entities"));
        worlds = section.getStringList("worlds");
    }

    @Override
    public void activate(LivingEntity target, World world) {
        String name = Util.getName(target);
    }

    @Override
    public boolean canActivate(LivingEntity target, World world) {
        if (!worlds.contains(world.getName()))
            return false;
        if (!types.contains(target.getType()))
            return false;
        if (!target.isOnGround())
            return false;
        if (target instanceof Player)
            return ((Player) target).getFoodLevel() <= 5;
        return RANDOM.nextDouble() <= chance;
    }

    @Override
    public String getSectionName() {
        return "pooping_animal";
    }

    @Override
    public String toString() {
        return "PoopingAnimalAction{" +
                "types=" + types +
                ", chance=" + chance +
                '}';
    }
}
