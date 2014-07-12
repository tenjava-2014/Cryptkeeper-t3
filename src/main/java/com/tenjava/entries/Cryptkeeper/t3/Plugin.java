package com.tenjava.entries.Cryptkeeper.t3;

import com.tenjava.entries.Cryptkeeper.t3.actions.ExplodingChickenAction;
import com.tenjava.entries.Cryptkeeper.t3.actions.FlyingAnimalAction;
import com.tenjava.entries.Cryptkeeper.t3.actions.LeapingAnimalAction;
import com.tenjava.entries.Cryptkeeper.t3.actions.MeteorAction;
import com.tenjava.entries.Cryptkeeper.t3.actions.PoopingAnimalAction;
import com.tenjava.entries.Cryptkeeper.t3.actions.RandomAgeAction;
import com.tenjava.entries.Cryptkeeper.t3.actions.VampirePigAction;
import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class Plugin extends JavaPlugin {

    private static Plugin instance;
    private final Set<ActionHandler> actions = new HashSet<>();

    @Override
    public void onEnable() {
        instance = this;

        addHandler(new ExplodingChickenAction());
        addHandler(new LeapingAnimalAction());
        addHandler(new PoopingAnimalAction());
        addHandler(new VampirePigAction());
        addHandler(new FlyingAnimalAction());
        addHandler(new RandomAgeAction());
        addHandler(new MeteorAction());

        for (ActionHandler handler : actions) {
            if (!getConfig().getStringList("disabled").contains(handler.getSectionName()))
                handler.load(getConfig().getConfigurationSection(handler.getSectionName()));
        }
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        getServer().getScheduler().cancelTasks(this);
    }

    private void addHandler(ActionHandler handler) {
        getLogger().info("Loaded: " + handler.getClass().getSimpleName());
        actions.add(handler);
    }

    public static Plugin getInstance() {
        return instance;
    }
}
