package fr.skywors.isitwaxed.client;

import fr.skywors.isitwaxed.IsItWaxed;
import fr.skywors.isitwaxed.client.config.Config;
import fr.skywors.isitwaxed.client.utils.CrosshairObserver;
import fr.skywors.isitwaxed.client.utils.HudRender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Oxidizable;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;

public class IsItWaxedClient implements ClientModInitializer {
	public static Config config;
	public static boolean reconfigureEnabled = false;
	public static Boolean enabled = true;
	public static Boolean isAxeNeeded = false;

	@Override
	public void onInitializeClient() {
		if (FabricLoader.getInstance().isModLoaded("reconfigure")) {
			reconfigureEnabled = true;
			config = new Config();
			config.load();
		}

		HudElementRegistry.addLast(Identifier.of(IsItWaxed.MOD_ID), (drawContext, tickDelta) -> {
			MinecraftClient minecraftClient = MinecraftClient.getInstance();
			ClientWorld world = minecraftClient.world;

			if (reconfigureEnabled) {
				isAxeNeeded = config.isAxeNeeded.get();
				enabled = config.enabled.get();
			}

			if (
				enabled
				&& world != null
				&& minecraftClient.player != null
				&& minecraftClient.crosshairTarget instanceof BlockHitResult crosshairTarget
				&& (isAxeNeeded ? (
					(minecraftClient.player.getMainHandStack().getItem() instanceof AxeItem)
					|| minecraftClient.player.getMainHandStack().getItem().equals(Items.HONEYCOMB)
				) : true)
			) {
				Block block = CrosshairObserver.getTargetBlock(crosshairTarget);

				if (block.getName().toString().toLowerCase().contains("waxed")) {
					HudRender.renderWaxedIcon(drawContext, minecraftClient, true);
				} else {
					if (
						block instanceof Oxidizable
					) {
						HudRender.renderWaxedIcon(drawContext, minecraftClient, false);
					}
				}
			}
		});
	}
}
