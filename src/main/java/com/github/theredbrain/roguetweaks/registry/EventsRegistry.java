package com.github.theredbrain.roguetweaks.registry;

import com.github.theredbrain.roguetweaks.network.event.PlayerDeathCallback;
import com.github.theredbrain.roguetweaks.network.event.PlayerJoinCallback;
import com.github.theredbrain.roguetweaks.network.event.PlayerLeaveCallback;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class EventsRegistry {
    public static void initializeEvents() {

        PlayerJoinCallback.EVENT.register((player, server) -> {
            int minOfflineTimeToTeleportToSpawn = server.getGameRules().getInt(GameRulesRegistry.MIN_OFFLINE_TICKS_TO_TELEPORT_TO_SPAWN);
            if ((minOfflineTimeToTeleportToSpawn > -1) && (Math.abs(server.getOverworld().getTime() - ComponentsRegistry.LAST_LOGOUT_TIME.get(player).getValue()) >= minOfflineTimeToTeleportToSpawn)) {
                ServerWorld serverWorld = server.getWorld(player.getSpawnPointDimension());
                BlockPos spawnPoint = player.getSpawnPointPosition();
                if (serverWorld == null) {
                    serverWorld = server.getOverworld();
                }
                if (spawnPoint == null) {
                    spawnPoint = serverWorld.getSpawnPos();
                }
                player.teleport(serverWorld, spawnPoint.getX() + 0.5, spawnPoint.getY(), spawnPoint.getZ() + 0.5, serverWorld.getSpawnAngle(), 0.0F);
            }
        });
        PlayerDeathCallback.EVENT.register((player, server, source) -> {
            if (server.getGameRules().getBoolean(GameRulesRegistry.RESET_ADVANCEMENTS_ON_DEATH)) {
                player.getAdvancementTracker().reload(server.getAdvancementLoader());
            }
            if (server.getGameRules().getBoolean(GameRulesRegistry.RESET_RECIPES_ON_DEATH)) {
                player.getRecipeBook().lockRecipes(server.getRecipeManager().values(), player);
            }
        });
        PlayerLeaveCallback.EVENT.register((player, server) -> ComponentsRegistry.LAST_LOGOUT_TIME.get(player).setValue(server.getOverworld().getTime()));
    }
}
