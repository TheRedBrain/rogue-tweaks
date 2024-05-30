package com.github.theredbrain.roguetweaks.registry;

import com.github.theredbrain.roguetweaks.RogueTweaks;
import com.github.theredbrain.roguetweaks.components.LongComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;

public final class ComponentsRegistry implements EntityComponentInitializer {
    public static final ComponentKey<LongComponent> LAST_LOGOUT_TIME =
            ComponentRegistry.getOrCreate(RogueTweaks.identifier("last_logout_time"), LongComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(LAST_LOGOUT_TIME, e -> new LongComponent(), RespawnCopyStrategy.ALWAYS_COPY);
    }
}
