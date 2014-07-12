package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import com.tenjava.entries.Cryptkeeper.t3.util.Util;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.List;

public class PoopingAnimalAction extends ActionHandler<LivingEntity> {

    private List<EntityType> types;

    @Override
    public void load(ConfigurationSection section) {
        super.load(section);
        types = Util.getSafeEntities(section.getStringList("entities"));
    }

    @Override
    public void activate(LivingEntity target, World world) {
        String name = Util.getName(target);
    }

    @Override
    public boolean canActivate(LivingEntity target, World world) {
        if (!super.canActivate(target, world))
            return false;
        if (!types.contains(target.getType()))
            return false;
        if (!target.isOnGround())
            return false;
        if (target instanceof Player)
            return ((Player) target).getFoodLevel() <= 5;
        return random.nextDouble() <= chance;
    }

    @Override
    public String getSectionName() {
        return "pooping_animal";
    }

    @Override
    public String toString() {
        return "PoopingAnimalAction{" +
                "types=" + types +
                '}';
    }
}
