package com.tenjava.entries.Cryptkeeper.t3.api;

import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class ActionHandler<T> {

    protected final List<String> worlds = new ArrayList<>();
    protected final Random random = new Random();
    protected double chance = 0;

    public void load(ConfigurationSection section) {
        worlds.addAll(section.getStringList("worlds"));
        chance = section.getDouble("chance");
    }

    public abstract void register();

    public abstract void activate(T target, World world);

    public boolean canActivate(T target, World world) {
        if (!worlds.contains(world.getName()))
            return false;
        return true;
    }

    public abstract String getSectionName();
}
