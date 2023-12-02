package net.william278.shulkolored;

import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Shulker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;

public final class Shulkolored extends JavaPlugin implements Listener {

    private static final float COLORED_SHULKER_RATE = 0.1f;
    private final Random random = new Random();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Shulkolored has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Shulkolored has been disabled!");
    }

    @EventHandler
    public void onShulkerSpawn(@NotNull CreatureSpawnEvent event) {
        if (event.getEntityType() == EntityType.SHULKER) {
            colorShulker((Shulker) event.getEntity());
        }
    }

    @EventHandler
    public void onChunkLoad(@NotNull ChunkLoadEvent event) {
        if (event.isNewChunk()) {
            Arrays.stream(event.getChunk().getEntities())
                    .filter(entity -> entity.getType() == EntityType.SHULKER)
                    .forEach(entity -> colorShulker((Shulker) entity));
        }
    }

    private void colorShulker(Shulker shulker) {
        if (random.nextFloat() <= COLORED_SHULKER_RATE) {
            shulker.setColor(DyeColor.values()[random.nextInt(DyeColor.values().length)]);
        }
    }

}
