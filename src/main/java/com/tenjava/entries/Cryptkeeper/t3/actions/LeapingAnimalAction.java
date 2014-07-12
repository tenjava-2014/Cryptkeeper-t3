package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import com.tenjava.entries.Cryptkeeper.t3.util.Util;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.List;
import java.util.Random;

public class LeapingAnimalAction implements ActionHandler<LivingEntity> {

    private final static Random RANDOM = new Random();

    private List<EntityType> types;
    private double chance;

    @Override
    public void load(ConfigurationSection section) {
        chance = section.getDouble("chance");
        types = Util.getSafeEntities(section.getStringList("entities"));
        System.out.println("Loaded: " + toString());
    }

    @Override
    public void activate(LivingEntity target) {
        System.out.println("Leaping: " + target.toString());
    }

    @Override
    public boolean canActivate(LivingEntity target) {
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
                "types=" + types +
                ", chance=" + chance +
                '}';
    }
}
