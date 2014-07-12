package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import com.tenjava.entries.Cryptkeeper.t3.util.Util;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class PoopingAnimalAction implements ActionHandler<LivingEntity> {

    @Override
    public void load(ConfigurationSection section) {

    }

    @Override
    public void activate(LivingEntity target) {
        String name = Util.getName(target);
        System.out.println("Pooping: " + name);
    }

    @Override
    public boolean canActivate(LivingEntity target) {
        if (target instanceof Player)
            return ((Player) target).getFoodLevel() <= 5;
        return true;
    }

    @Override
    public String getSectionName() {
        return "pooping_animal";
    }
}
