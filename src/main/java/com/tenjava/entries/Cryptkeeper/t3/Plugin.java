package com.tenjava.entries.Cryptkeeper.t3;

import com.tenjava.entries.Cryptkeeper.t3.actions.LeapingAnimalAction;
import com.tenjava.entries.Cryptkeeper.t3.actions.PoopingAnimalAction;
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

        /*try {
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                saveDefaultConfig();
            }
        } catch (Exception ex) {
            getLogger().severe("Failed to save/load configuration file!");
            ex.printStackTrace();
        }*/

        actions.add(new PoopingAnimalAction());
        actions.add(new LeapingAnimalAction());

        for (ActionHandler handler : actions) {
            handler.load(getConfig().getConfigurationSection(handler.getSectionName()));
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
