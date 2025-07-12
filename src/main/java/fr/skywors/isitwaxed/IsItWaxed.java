package fr.skywors.isitwaxed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;

public class IsItWaxed implements ModInitializer {
	public static final String MOD_ID = "isitwaxed";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("IsItWaxed initialized!");
	}
}
