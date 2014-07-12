package com.tenjava.entries.Cryptkeeper.t3.util;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * THIS CLASS IS A STUB IN CASE IT IS NEEDED IN THE FUTURE
 */
public class EntityTracker implements Listener {

    private static EntityTracker instance;
    private final Map<String, List<Entity>> entities = new HashMap<>();

    private EntityTracker() {

    }

    public void refresh(World world) {
        entities.put(world.getName(), world.getEntities());
    }

    private void clear(String world) {
        Iterator<Entity> itl = entities.get(world).iterator();
        while (itl.hasNext()) {
            Entity entity = itl.next();
            if (!entity.isValid()) {
                itl.remove();
            }
        }
    }

    public static EntityTracker getInstance() {
        if (instance == null) {
            instance = new EntityTracker();
        }
        return instance;
    }
}
