package fr.skywors.isitwaxed.client.config;

import java.net.URI;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

public class ModConfigScreen {
	public static Screen create(Screen parent) {
		if (FabricLoader.getInstance().isModLoaded("reconfigure")) {
			try {
				return ReconfigureHelper.createConfigScreen(parent);
			} catch (Exception e) {
				return createFallbackScreen(parent);
			}
		} else {
			return createFallbackScreen(parent);
		}
	}

	private static Screen createFallbackScreen(Screen parent) {
		return new ConfirmScreen(result -> {
			if (result) {
				Util.getOperatingSystem().open(URI.create("https://modrinth.com/mod/reconfigure"));
			}

			MinecraftClient.getInstance().setScreen(parent);
		},
			Text.translatable("isitwaxed.reconfigure.missing"),
			Text.translatable("isitwaxed.reconfigure.missing.message"),
			ScreenTexts.YES,
			ScreenTexts.NO
		);
	}
}
