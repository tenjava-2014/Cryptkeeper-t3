package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;

public class VampirePigAction extends ActionHandler<LivingEntity> {

    @Override
    public void register() {

    }

    @Override
    public void activate(LivingEntity target, World world) {
        System.out.println("Activating: " + getClass().getSimpleName());
        target.damage(target.getHealth());
        target.getWorld().createExplosion(target.getLocation(), random.nextFloat() + 1F, true);
    }

    @Override
    public boolean canActivate(LivingEntity target, World world) {
        if (!super.canActivate(target, world))
            return false;
        if (!(target instanceof Pig))
            return false;
        return random.nextDouble() <= chance;
    }

    @Override
    public String getSectionName() {
        return "vampire_pig";
    }
}
