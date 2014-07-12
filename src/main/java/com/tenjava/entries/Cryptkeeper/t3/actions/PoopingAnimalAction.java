package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.Plugin;
import com.tenjava.entries.Cryptkeeper.t3.api.EntityActionHandler;
import com.tenjava.entries.Cryptkeeper.t3.utilities.Util;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PoopingAnimalAction extends EntityActionHandler<LivingEntity> {

    @Override
    public void register() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Plugin.getInstance(), new Runnable() {

            @Override
            public void run() {
                for (LivingEntity entity : Util.getActiveEntities(worlds)) {
                    if (canActivate(entity, entity.getWorld())) {
                        activate(entity, entity.getWorld());
                    }
                }
            }
        }, 40L, 40L);
    }

    @Override
    public void activate(LivingEntity target, World world) {
        world.playSound(target.getLocation(), Sound.CHICKEN_EGG_POP, 1F, 1F);
        Item item = world.dropItem(target.getLocation(), new ItemStack(Material.INK_SACK, 1, DyeColor.BROWN.getDyeData()));
        item.setPickupDelay(Integer.MAX_VALUE);
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
            return ((Player) target).getFoodLevel() <= 5 && random.nextDouble() <= chance;
        return random.nextDouble() <= chance;
    }

    @Override
    public String getSectionName() {
        return "pooping_animal";
    }
}
