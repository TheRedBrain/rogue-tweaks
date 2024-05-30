package com.github.theredbrain.roguetweaks;

import com.github.theredbrain.roguetweaks.registry.EventsRegistry;
import com.github.theredbrain.roguetweaks.registry.GameRulesRegistry;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RogueTweaks implements ModInitializer {
	public static final String MOD_ID = "roguetweaks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Rogue Tweaks initialized");

		// Registry
		EventsRegistry.initializeEvents();
		GameRulesRegistry.init();
	}

	public static Identifier identifier(String path) {
		return Identifier.of(MOD_ID, path);
	}

}