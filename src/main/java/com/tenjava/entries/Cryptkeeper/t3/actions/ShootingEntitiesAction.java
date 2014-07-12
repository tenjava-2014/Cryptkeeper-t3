package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.api.EntityActionHandler;
import org.bukkit.World;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.SmallFireball;

public class ShootingEntitiesAction extends EntityActionHandler<LivingEntity> {

    @Override
    public void register() {

    }

    @Override
    public void activate(LivingEntity target, World world) {
        target.launchProjectile(random.nextBoolean() ? LargeFireball.class : SmallFireball.class);
    }

    @Override
    public boolean canActivate(LivingEntity target, World world) {
        if (!super.canActivate(target, world))
            return false;
        if (!types.contains(target.getType()))
            return false;
        return true;
    }

    @Override
    public String getSectionName() {
        return "shooting_entities";
    }
}
