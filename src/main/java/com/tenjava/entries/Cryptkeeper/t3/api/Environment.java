package com.tenjava.entries.Cryptkeeper.t3.api;

import org.bukkit.Material;

import java.util.Random;

public abstract class Environment {

    protected final Random random = new Random();

    public abstract Material getMaterial(int y);

    public abstract double getChance();
}
