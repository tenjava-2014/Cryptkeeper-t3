package com.tenjava.entries.Cryptkeeper.t3.actions;

import com.tenjava.entries.Cryptkeeper.t3.Plugin;
import com.tenjava.entries.Cryptkeeper.t3.api.ActionHandler;
import com.tenjava.entries.Cryptkeeper.t3.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MeteorAction implements ActionHandler<Player>, Listener {

    private final static Random RANDOM = new Random();

    private final Set<FallingBlock> entities = new HashSet<>();
    private List<Material> materials;
    private List<String> worlds;
    private double chance, oreChance;
    private int minSize, maxSize;
    private Material fallback;

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (event.getEntity() instanceof FallingBlock && event.getBlock().getType().equals(Material.AIR)) {
            entities.remove(event.getEntity());
            if (RANDOM.nextBoolean()) {
                event.setCancelled(true);
            }
        }
    }

    @Override
    public void load(ConfigurationSection section) {
        chance = section.getDouble("chance");
        worlds = section.getStringList("worlds");
        materials = Util.getSafeMaterials(section.getStringList("ores"));
        oreChance = section.getDouble("ore_chance");
        fallback = Material.valueOf(section.getString("default"));
        minSize = section.getInt("min_size");
        maxSize = section.getInt("max_size");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Plugin.getInstance(), new Runnable() {

            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (canActivate(player, player.getWorld())) {
                        activate(player, player.getWorld());
                    }
                }
            }
        }, 5 * 20L, 30 * 20L);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Plugin.getInstance(), new Runnable() {

            @Override
            public void run() {
                Iterator<FallingBlock> itl = entities.iterator();
                while (itl.hasNext()) {
                    FallingBlock block = itl.next();
                    if (!block.isValid()) {
                        itl.remove();
                    } else {
                        if (block.isOnGround()) {
                            block.getWorld().createExplosion(block.getLocation(), 0.75F, true);
                        }
                    }
                }
            }
        }, 20L, 20L);
    }

    @Override
    public void activate(Player target, World world) {
        Location location = target.getLocation();
        location.setY(255);
        location.add((RANDOM.nextBoolean() ? -1 : 1) * RANDOM.nextInt(25), 0, (RANDOM.nextBoolean() ? -1 : 1) * RANDOM.nextInt(25));
        int size = RANDOM.nextInt(maxSize - minSize) + minSize;
        for (int x = -size; x < size + 1; x++) {
            for (int z = -size; z < size + 1; z++) {
                for (int y = -size; y < size; y++) {
                    Location current = location.clone().add(x, y, z);
                    if (current.distanceSquared(location) > Math.pow(size, 2)) {
                        continue;
                    }
                    Material material = fallback;
                    if (RANDOM.nextDouble() <= oreChance) {
                        material = materials.get(RANDOM.nextInt(materials.size()));
                    }
                    FallingBlock block = world.spawnFallingBlock(current, material, (byte) 0);
                    block.setDropItem(false);
                    block.setFireTicks(Integer.MAX_VALUE);
                    block.setVelocity(new Vector(0, -2, 0));
                    entities.add(block);
                }
            }
        }
    }

    @Override
    public boolean canActivate(Player target, World world) {
        if (!worlds.contains(world.getName()))
            return false;
        if (!target.isOnGround())
            return false;
        if (target.getGameMode().equals(GameMode.CREATIVE))
            return false;
        return RANDOM.nextDouble() <= chance;
    }

    @Override
    public String getSectionName() {
        return "meteor";
    }
}
