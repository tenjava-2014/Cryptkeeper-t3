package com.tenjava.entries.Cryptkeeper.t3.api;

import com.tenjava.entries.Cryptkeeper.t3.utilities.Util;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public abstract class EntityActionHandler<T> extends ActionHandler<T> {

    protected final List<EntityType> types = new ArrayList<>();

    @Override
    public void load(ConfigurationSection section) {
        super.load(section);
        types.addAll(Util.getSafeEntities(section.getStringList("entities")));
    }
}
