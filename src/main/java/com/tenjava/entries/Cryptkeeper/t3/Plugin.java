package com.tenjava.entries.Cryptkeeper.t3;

import com.tenjava.entries.Cryptkeeper.t3.actions.ExplodingMobAction;
import com.tenjava.entries.Cryptkeeper.t3.actions.FlippedMobAction;
import com.tenjava.entries.Cryptkeeper.t3.actions.FlyingAnimalAction;
import com.tenjava.entries.Cryptkeeper.t3.actions.LeapingAnimalAction;
import com.tenjava.entries.Cryptkeeper.t3.actions.MobColumnAction;
import com.tenjava.entries.Cryptkeeper.t3.actions.MobNameAction;
import com.tenjava.entries.Cryptkeeper.t3.actions.PoopingAnimalAction;
import com.tenjava.entries.Cryptkeeper.t3.actions.ShootingEntitiesAction;
import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Plugin extends JavaPlugin {

    private static Plugin instance;
    private final Set<ActionHandler> actions = new HashSet<>();

    @Override
    public void onEnable() {
        instance = this;

        actions.add(new ExplodingMobAction());
        actions.add(new LeapingAnimalAction());
        actions.add(new PoopingAnimalAction());
        actions.add(new FlyingAnimalAction());
        actions.add(new MobColumnAction());
        actions.add(new ShootingEntitiesAction());
        actions.add(new FlippedMobAction());
        actions.add(new MobNameAction());

        Iterator<ActionHandler> itl = actions.iterator();
        while (itl.hasNext()) {
            ActionHandler handler = itl.next();
            if (!getConfig().getStringList("disabled").contains(handler.getSectionName())) {
                handler.load(getConfig().getConfigurationSection(handler.getSectionName()));
                getLogger().info("Enabled: " + handler.getSectionName());
                continue;
            }
            getLogger().info("Disabled: " + handler.getSectionName());
            itl.remove();
        }

        for (ActionHandler handler : actions) {
            handler.register();
        }
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        getServer().getScheduler().cancelTasks(this);
    }

    public static Plugin getInstance() {
        return instance;
    }
}
