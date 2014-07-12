package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.api.EntityActionHandler;
import com.tenjava.entries.Cryptkeeper.t3.util.Util;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class PoopingAnimalAction extends EntityActionHandler<LivingEntity> {

    @Override
    public void register() {

    }

    @Override
    public void activate(LivingEntity target, World world) {
        System.out.println("Activating: " + getClass().getSimpleName());
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
}
