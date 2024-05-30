package com.github.theredbrain.roguetweaks.mixin.server.network;

import com.github.theredbrain.roguetweaks.network.event.PlayerLeaveCallback;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {

    @Shadow
    public ServerPlayerEntity player;

    @Inject(at = @At(value = "HEAD"), method = "onDisconnected")
    private void roguetweaks$onDisconnected(Text reason, CallbackInfo info) {
        PlayerLeaveCallback.EVENT.invoker().leaveServer(this.player, this.player.getServer());
    }
}