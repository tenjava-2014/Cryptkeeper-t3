package com.tenjava.entries.Cryptkeeper.t3.api;

import org.bukkit.Location;

public interface Structure {

    public void spawn(Location location);

    public boolean canSpawn(Location location);

    public String getName();
}
