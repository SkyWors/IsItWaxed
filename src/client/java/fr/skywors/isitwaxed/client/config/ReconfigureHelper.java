package fr.skywors.isitwaxed.client.config;

import fr.skywors.isitwaxed.client.IsItWaxedClient;
import net.minecraft.client.gui.screen.Screen;

public class ReconfigureHelper {
	public static Screen createConfigScreen(Screen parent) {
		return IsItWaxedClient.config.config.createScreen(parent);
	}
}
