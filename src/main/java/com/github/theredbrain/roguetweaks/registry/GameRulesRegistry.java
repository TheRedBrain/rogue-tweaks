package com.github.theredbrain.roguetweaks.registry;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class GameRulesRegistry {
    public static final GameRules.Key<GameRules.BooleanRule> CLEAR_ENDER_CHEST_ON_DEATH =
            GameRuleRegistry.register("clearEnderChestOnDeath", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> RESET_ADVANCEMENTS_ON_DEATH =
            GameRuleRegistry.register("resetAdvancementsOnDeath", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.BooleanRule> RESET_RECIPES_ON_DEATH =
            GameRuleRegistry.register("resetRecipesOnDeath", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(false));
    public static final GameRules.Key<GameRules.IntRule> MIN_OFFLINE_TICKS_TO_TELEPORT_TO_SPAWN =
            GameRuleRegistry.register("minOfflineTicksToTeleportToSpawn", GameRules.Category.PLAYER, GameRuleFactory.createIntRule(0, -1));
    public static void init() {}
}
