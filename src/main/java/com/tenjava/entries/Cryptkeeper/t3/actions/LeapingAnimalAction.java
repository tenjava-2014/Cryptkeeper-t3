package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.api.EntityActionHandler;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class LeapingAnimalAction extends EntityActionHandler<LivingEntity> {

    @Override
    public void register() {

    }

    @Override
    public void activate(LivingEntity target, World world) {
        target.setVelocity(new Vector(0, 1, 0));
    }

    @Override
    public boolean canActivate(LivingEntity target, World world) {
        if (!super.canActivate(target, world))
            return false;
        if (!types.contains(target.getType()))
            return false;
        if (!target.isOnGround())
            return false;
        return random.nextDouble() <= chance;
    }

    @Override
    public String getSectionName() {
        return "leaping_animal";
    }
}
