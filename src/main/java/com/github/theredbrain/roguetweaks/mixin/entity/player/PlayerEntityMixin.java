package com.github.theredbrain.roguetweaks.mixin.entity.player;

import com.github.theredbrain.roguetweaks.registry.GameRulesRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    @Shadow protected EnderChestInventory enderChestInventory;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "dropInventory", at = @At("TAIL"))
    protected void roguetweaks$dropInventory(CallbackInfo ci) {
        if (this.getWorld().getGameRules().getBoolean(GameRulesRegistry.CLEAR_ENDER_CHEST_ON_DEATH)) {
            this.enderChestInventory.clear();
        }
    }
}
